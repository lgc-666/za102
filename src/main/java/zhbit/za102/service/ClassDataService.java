package zhbit.za102.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.ClassData;
import zhbit.za102.bean.ClassDataExample;
import zhbit.za102.bean.Msg;
import zhbit.za102.dao.ClassDataMapper;

import java.util.List;

@Service
@CacheConfig(cacheNames = "ClassData")
public class ClassDataService {
    @Autowired
    ClassDataMapper classDataMapper;

    @CacheEvict(allEntries = true)
    public void add(ClassData u) {
        classDataMapper.insert(u);
    }

    @CacheEvict(allEntries = true)
    public void delete(Integer id) {
        classDataMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(allEntries = true)
    public void update(ClassData u) {
        classDataMapper.updateByPrimaryKeySelective(u);
    }

    @Cacheable(key = "'get'+'-'+#id")
    public ClassData get(Integer id) {
        return classDataMapper.selectByPrimaryKey(id);
    }

    public List<ClassData> list() {
        ClassDataExample example = new ClassDataExample();
        example.setOrderByClause("id desc");
        return classDataMapper.selectByExample(example);
    }

    @Cacheable(key = "'list'+'-'+#start+'-'+#size")
    public Msg list(int start, int size) {
        PageHelper.startPage(start, size, "id desc");
        List<ClassData> us = list();
        PageInfo<ClassData> page = new PageInfo<>(us);
        return new Msg(page);
    }
}
