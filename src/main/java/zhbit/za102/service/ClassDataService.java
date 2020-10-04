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

    @CacheEvict(value="ClassData",allEntries = true)
    public void add(ClassData u) {
        classDataMapper.insert(u);
    }

    @CacheEvict(value="ClassData",allEntries = true)
    public void delete(Integer id) {
        classDataMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value="ClassData",allEntries = true)
    public void update(ClassData u) {
        classDataMapper.updateByPrimaryKeySelective(u);
    }

    @Cacheable(value="ClassData",key = "'get'+'-'+#id")
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


    public Integer selectWithin1hour(){
        return classDataMapper.selectWithin1hour();
    }
    public Integer selectWithin1hourByClass(){
        return classDataMapper.selectWithin1hourByClass();
    }
    public void updateWithin1hour(Integer hours,Integer num){ classDataMapper.updateWithin1hour(hours,num); }
    public void insertClassData(String address,Integer hours){ classDataMapper.insertClassData(address,hours); }

}
