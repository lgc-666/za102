package zhbit.za102.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.Class;
import zhbit.za102.bean.ClassExample;
import zhbit.za102.bean.Msg;
import zhbit.za102.dao.ClassMapper;

import java.util.List;

@Service
@CacheConfig(cacheNames = "Class")
public class ClassService {
    @Autowired
    ClassMapper classMapper;

    @CacheEvict(allEntries = true)
    public void add(Class u) {
        classMapper.insert(u);
    }

    @CacheEvict(allEntries = true)
    public void delete(Integer id) { classMapper.deleteByPrimaryKey(id); }

    @CacheEvict(allEntries = true)
    public void update(Class u) { classMapper.updateByPrimaryKeySelective(u); }

    @Cacheable(key = "'get'+'-'+#id")
    public Class get(Integer id) {
        return classMapper.selectByPrimaryKey(id);
    }


    public List<Class> list() {
        ClassExample example = new ClassExample();
        example.setOrderByClause("classid desc");
        return classMapper.selectByExample(example);
    }

    @Cacheable(key = "'list'+'-'+#start+'-'+#size")
    public Msg list(int start, int size) {
        PageHelper.startPage(start, size, "classid desc");
        List<Class> us = list();
        PageInfo<Class> page = new PageInfo<>(us);
        return new Msg(page);
    }
}
