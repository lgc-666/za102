package zhbit.za102.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.Machine;
import zhbit.za102.bean.MachineExample;
import zhbit.za102.dao.MachineMapper;

import java.util.List;

@Service
public class MachineService {
    @Autowired
    MachineMapper machineMapper;

    public void add(Machine u) {
        machineMapper.insert(u);
    }


    public void delete(Integer id) {
        machineMapper.deleteByPrimaryKey(id);
    }


    public void update(Machine u) {
        machineMapper.updateByPrimaryKeySelective(u);
    }


    public Machine get(Integer id) {
        return machineMapper.selectByPrimaryKey(id);
    }


    public List<Machine> list() {
        MachineExample example = new MachineExample();
        example.setOrderByClause("mid desc");
        return machineMapper.selectByExample(example);
    }

}
