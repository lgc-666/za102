package zhbit.za102.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.User;
import zhbit.za102.bean.UserExample;
import zhbit.za102.dao.UserMapper;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleService userRoleService;

    public String getPassword(String name) {
        User user = getByName(name);
        if (null == user)
            return null;
        return user.getPassword();
    }

    public User getByName(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(name);
        List<User> users = userMapper.selectByExample(example);
        if (users.isEmpty())
            return null;
        return users.get(0);
    }

    public void add(User u) {
        userMapper.insert(u);
    }

    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
        userRoleService.deleteByUser(id);
    }

    public void update(User u) {
        userMapper.updateByPrimaryKeySelective(u);
    }

    public User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> list() {
        UserExample example = new UserExample();
        example.setOrderByClause("uid desc");
        return userMapper.selectByExample(example);

    }
}

