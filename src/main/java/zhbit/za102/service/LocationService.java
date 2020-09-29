package zhbit.za102.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.Location;
import zhbit.za102.bean.LocationExample;
import zhbit.za102.bean.Msg;
import zhbit.za102.dao.LocationMapper;

import java.util.List;

@Service
@CacheConfig(cacheNames = "Location")
public class LocationService {
    @Autowired
    LocationMapper locationMapper;

    @CacheEvict(value="Location",allEntries = true)
    public void add(Location u) {
        locationMapper.insert(u);
    }

    @CacheEvict(value="Location",allEntries = true)
    public void delete(Integer id) {
        locationMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value="Location",allEntries = true)
    public void update(Location u) {
        locationMapper.updateByPrimaryKeySelective(u);
    }

    @Cacheable(value="Location",key = "'get'+'-'+#id")
    public Location get(Integer id) {
        return locationMapper.selectByPrimaryKey(id);
    }

    public List<Location> list() {
        LocationExample example = new LocationExample();
        example.setOrderByClause("locationid desc");
        return locationMapper.selectByExample(example);
    }

    @Cacheable(value="Location",key = "'list'+'-'+#start+'-'+#size")
    public Msg list(int start, int size) {
        PageHelper.startPage(start, size, "locationid desc");
        List<Location> us = list();
        PageInfo<Location> page = new PageInfo<>(us);
        return new Msg(page);
    }
}
