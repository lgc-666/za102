package zhbit.za102.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.*;
import zhbit.za102.dao.RoleMapper;
import zhbit.za102.dao.UserRoleMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@CacheConfig(cacheNames = "Role")
public class RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    UserService userService;

    @Cacheable(key = "'listRoleNames'+'-'+#userName")
    public Set<String> listRoleNames(String userName) {  //去重
        Set<String> result = new HashSet<>();
        List<Role> roles = listRoles(userName);
        for (Role role : roles) {
            result.add(role.getName());
        }
        return result;
    }

    @Cacheable(key = "'listRoles'+'-'+#userName")
    public List<Role> listRoles(String userName) {
        List<Role> roles = new ArrayList<>();
        User user = userService.getByName(userName);
        if (null == user)
            return roles;
        roles = listRoles(user);
        return roles;
    }

    @Cacheable(key = "'listRoles'")
    public List<Role> list() {
        RoleExample example = new RoleExample();
        example.setOrderByClause("rid desc");
        return roleMapper.selectByExample(example);

    }

    @Cacheable(key = "'listRoles'+'-'+#user")
    public List<Role> listRoles(User user) {
        List<Role> roles = new ArrayList<>();

        UserRoleExample example = new UserRoleExample();

        example.createCriteria().andUidEqualTo(user.getUid());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);

        for (UserRole userRole : userRoles) {
            Role role = roleMapper.selectByPrimaryKey(userRole.getRid());
            roles.add(role);
        }
        return roles;
    }

    @CacheEvict(allEntries = true)
    public void add(Role u) {
        roleMapper.insert(u);
    }

    @CacheEvict(allEntries = true)
    public void delete(Integer id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(allEntries = true)
    public void update(Role u) {
        roleMapper.updateByPrimaryKeySelective(u);
    }

    @Cacheable(key = "'Role'+'-'+#id")
    public Role get(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }


}
