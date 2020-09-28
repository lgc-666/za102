package zhbit.za102.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.Class;
import zhbit.za102.bean.Msg;
import zhbit.za102.bean.Visit;
import zhbit.za102.bean.VisitExample;
import zhbit.za102.dao.VisitMapper;

import java.util.List;

@Service
@CacheConfig(cacheNames = "Visit")
public class VisitService {
    @Autowired
    VisitMapper visitMapper;

    @CacheEvict(allEntries = true)
    public void add(Visit u) {
        visitMapper.insert(u);
    }

    @CacheEvict(allEntries = true)
    public void delete(Integer id) {
        visitMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(allEntries = true)
    public void update(Visit u) {
        visitMapper.updateByPrimaryKeySelective(u);
    }

    @Cacheable(key = "'get'+'-'+#id")
    public Visit get(Integer id) {
        return visitMapper.selectByPrimaryKey(id);
    }

    public List<Visit> list() {
        VisitExample example = new VisitExample();
        example.setOrderByClause("visitid desc");
        return visitMapper.selectByExample(example);
    }

    @Cacheable(key = "'list'+'-'+#start+'-'+#size")
    public Msg list(int start, int size) {
        PageHelper.startPage(start, size, "visitid desc");
        List<Visit> us = list();
        PageInfo<Visit> page = new PageInfo<>(us);
        return new Msg(page);
    }
}
