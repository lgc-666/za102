package zhbit.za102.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.Msg;
import zhbit.za102.bean.RegisterApproval;
import zhbit.za102.bean.RegisterApprovalExample;
import zhbit.za102.dao.RegisterApprovalMapper;

import java.util.List;

@Service
@CacheConfig(cacheNames = "RegisterApproval")
public class RegisterApprovalService {
    @Autowired
    RegisterApprovalMapper registerApprovalMapper;

    @CacheEvict(value="RegisterApproval",allEntries = true)
    public void add(RegisterApproval u) {
        registerApprovalMapper.insert(u);
    }

    @CacheEvict(value="RegisterApproval",allEntries = true)
    public void delete(Integer id) {
        registerApprovalMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value="RegisterApproval",allEntries = true)
    public void update(RegisterApproval u) {
        registerApprovalMapper.updateByPrimaryKeySelective(u);
    }

    @Cacheable(value="RegisterApproval",key = "'get'+'-'+#id")
    public RegisterApproval get(Integer id) {
        return registerApprovalMapper.selectByPrimaryKey(id);
    }

    public List<RegisterApproval> list() {
        RegisterApprovalExample example = new RegisterApprovalExample();
        example.setOrderByClause("id desc");
        return registerApprovalMapper.selectByExample(example);
    }

    @Cacheable(value="RegisterApproval",key = "'list'+'-'+#id")
    public List<RegisterApproval> list(Integer id) {
        RegisterApprovalExample example = new RegisterApprovalExample();
        example.createCriteria().andIdEqualTo(id);
        example.setOrderByClause("id desc");
        return registerApprovalMapper.selectByExample(example);
    }

    @Cacheable(value="RegisterApproval",key = "'list'+'-'+#start+'-'+#size")
    public Msg list(int start, int size) {
        PageHelper.startPage(start, size, "id desc");
        List<RegisterApproval> us = list();
        PageInfo<RegisterApproval> page = new PageInfo<>(us);
        return new Msg(page);
    }
}
