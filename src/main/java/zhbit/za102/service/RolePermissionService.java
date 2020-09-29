package zhbit.za102.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zhbit.za102.Utils.RedisUtils;
import zhbit.za102.bean.Role;
import zhbit.za102.bean.RolePermission;
import zhbit.za102.bean.RolePermissionExample;
import zhbit.za102.dao.RolePermissionMapper;

import java.util.List;

@Service
public class RolePermissionService {

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    RedisUtils redisUtil;

    @CacheEvict(value="Permission", allEntries=true)
    public void resetPermission(String status,Integer rid,Integer pid) {
        // 删除当前角色所有的权限
        if(status.equals("0")){
            deleteByRolePermission(rid,pid);
        }
        else{
            // 设置新的权限关系
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPid(pid);
            rolePermission.setRid(rid);
            rolePermissionMapper.insert(rolePermission);
        }
    }

    @CacheEvict(value="Permission", allEntries=true)
    public void setPermissions(Role role, Integer[] permissionIds) {
        // 删除当前角色所有的权限
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRidEqualTo(role.getRid());
        List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
        for (RolePermission rolePermission : rps)
            rolePermissionMapper.deleteByPrimaryKey(rolePermission.getId());

        // 设置新的权限关系
        if (null != permissionIds)
            for (Integer pid : permissionIds) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setPid(pid);
                rolePermission.setRid(role.getRid());
                rolePermissionMapper.insert(rolePermission);
            }
    }

    @CacheEvict(value="Permission", allEntries=true)
    public void deleteByRole(Integer roleId) {
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRidEqualTo(roleId);
        List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
        for (RolePermission rolePermission : rps)
            rolePermissionMapper.deleteByPrimaryKey(rolePermission.getId());
    }

    @CacheEvict(value="Permission", allEntries=true)
    public void deleteByPermission(Integer permissionId) {
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andPidEqualTo(permissionId);
        List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
        for (RolePermission rolePermission : rps)
            rolePermissionMapper.deleteByPrimaryKey(rolePermission.getId());
    }

    @CacheEvict(value="Permission", allEntries=true)
    public void deleteByRolePermission(Integer rid,Integer uid) {
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andPidEqualTo(uid);
        example.createCriteria().andPidEqualTo(rid);
        List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
        for (RolePermission rolePermission : rps)
            rolePermissionMapper.deleteByPrimaryKey(rolePermission.getId());
    }
}
