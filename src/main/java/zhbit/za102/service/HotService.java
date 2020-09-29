package zhbit.za102.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.Hot;
import zhbit.za102.bean.HotExample;
import zhbit.za102.bean.Msg;
import zhbit.za102.dao.HotMapper;

import java.util.List;

@Service
@CacheConfig(cacheNames = "Hot")
public class HotService {
    @Autowired
    HotMapper hotMapper;

    @CacheEvict(value="Hot",allEntries = true)
    public void add(Hot u) {
        hotMapper.insert(u);
    }

    @CacheEvict(value="Hot",allEntries = true)
    public void delete(Integer id) {
        hotMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value="Hot",allEntries = true)
    public void update(Hot u) {
        hotMapper.updateByPrimaryKeySelective(u);
    }

    @Cacheable(value="Hot",key = "'get'+'-'+#id")
    public Hot get(Integer id) {
        return hotMapper.selectByPrimaryKey(id);
    }

    public List<Hot> list() {
        HotExample example = new HotExample();
        example.setOrderByClause("hotid desc");
        return hotMapper.selectByExample(example);
    }

    @Cacheable(value="Hot",key = "'list'+'-'+#start+'-'+#size")
    public Msg list(int start, int size) {
        PageHelper.startPage(start, size, "hotid desc");
        List<Hot> us = list();
        PageInfo<Hot> page = new PageInfo<>(us);
        return new Msg(page);
    }
}
