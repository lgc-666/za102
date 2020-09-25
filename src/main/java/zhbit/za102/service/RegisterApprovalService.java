package zhbit.za102.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.RegisterApproval;
import zhbit.za102.bean.RegisterApprovalExample;
import zhbit.za102.dao.RegisterApprovalMapper;

import java.util.List;

@Service
public class RegisterApprovalService {
    @Autowired
    RegisterApprovalMapper registerApprovalMapper;

    public void add(RegisterApproval u) {
        registerApprovalMapper.insert(u);
    }


    public void delete(Integer id) {
        registerApprovalMapper.deleteByPrimaryKey(id);
    }


    public void update(RegisterApproval u) {
        registerApprovalMapper.updateByPrimaryKeySelective(u);
    }


    public RegisterApproval get(Integer id) {
        return registerApprovalMapper.selectByPrimaryKey(id);
    }


    public List<RegisterApproval> list() {
        RegisterApprovalExample example = new RegisterApprovalExample();
        example.setOrderByClause("id desc");
        return registerApprovalMapper.selectByExample(example);
    }

    public List<RegisterApproval> list(Integer id) {
        RegisterApprovalExample example = new RegisterApprovalExample();
        example.createCriteria().andIdEqualTo(id);
        example.setOrderByClause("id desc");
        return registerApprovalMapper.selectByExample(example);
    }
}
