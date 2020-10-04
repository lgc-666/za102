package zhbit.za102.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.Machine;
import zhbit.za102.bean.MachineExample;
import zhbit.za102.bean.Msg;
import zhbit.za102.dao.MachineMapper;

import java.util.List;

@Service
@CacheConfig(cacheNames = "Machine")
public class MachineService {
    @Autowired
    MachineMapper machineMapper;

    @CacheEvict(value="Machine",allEntries = true)
    public void add(Machine u) {
        machineMapper.insert(u);
    }

    @CacheEvict(value="Machine",allEntries = true)
    public void delete(Integer id) {
        machineMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value="Machine",allEntries = true)
    public void update(Machine u) {
        machineMapper.updateByPrimaryKeySelective(u);
    }

    @Cacheable(value="Machine",key = "'get'+'-'+#id")
    public Machine get(Integer id) {
        return machineMapper.selectByPrimaryKey(id);
    }

    public List<Machine> list() {
        MachineExample example = new MachineExample();
        example.setOrderByClause("mid desc");
        return machineMapper.selectByExample(example);
    }

    public List<Machine> listAll() {
        MachineExample example = new MachineExample();
        example.setOrderByClause("mid");
        return machineMapper.selectByExample(example);
    }

    @Cacheable(value="Machine",key = "'list'+'-'+#start+'-'+#size")
    public Msg list(int start, int size) {
        //start是当前第几页，size是每页显示几条，设置id倒排序
        PageHelper.startPage(start, size, "mid desc");
        List<Machine> us = list();
        PageInfo<Machine> page = new PageInfo<>(us);
        return new Msg(page);
    }
}
