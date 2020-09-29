package zhbit.za102.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.Msg;
import zhbit.za102.bean.Role;
import zhbit.za102.bean.User;
import zhbit.za102.bean.UserExample;
import zhbit.za102.dao.UserMapper;
import zhbit.za102.Utils.RedisUtils;

import java.util.List;

@Service
@CacheConfig(cacheNames = "User")
public class UserService {
    @Autowired
    RoleService roleService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleService userRoleService;

    RedisUtils redisUtil;
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

    @CacheEvict(value="User", allEntries=true)
    public void add(User u) {
        userMapper.insert(u);
    }

    @CacheEvict(value="User", allEntries=true)
    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
        userRoleService.deleteByUser(id);
    }

    @CacheEvict(value="User", allEntries=true)
    public void update(User u) {
        userMapper.updateByPrimaryKeySelective(u);
    }

    @Cacheable(value="User",key = "'get'+'-'+#id")
    public User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> list() {
        UserExample example = new UserExample();
        example.setOrderByClause("uid desc");
        return userMapper.selectByExample(example);
    }

    @Cacheable(value="User",key = "'list'+'-'+#start+'-'+#size")
    public Msg list(int start, int size) {
        PageHelper.startPage(start, size, "uid desc");
        List<User> us = list();
        //Map<String, List<User>> user_roles = new HashMap<>();
        for (User user : us) {
            List<Role> roles = roleService.listRoles(user);
            user.setRole(roles);
        }
        PageInfo<User> page = new PageInfo<>(us);
        //user_roles.put("listUser", us);
        return new Msg(page);
    }
}

