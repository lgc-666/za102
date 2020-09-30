package zhbit.za102.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zhbit.za102.Utils.RedisUtils;
import zhbit.za102.bean.*;
import zhbit.za102.dao.PermissionMapper;
import zhbit.za102.dao.RoleMapper;
import zhbit.za102.dao.RolePermissionMapper;

import java.util.*;

/**
 * 这里为了方便我把实现层给省去了
 */
@Service
@CacheConfig(cacheNames = "Permission")
public class PermissionService {
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    RoleMapper roleMapper;

    RedisUtils redisUtil;

    public Set<String> listPermissions(String userName) {
        Set<String> result = new HashSet<>();  //对结果进行去重
        List<Role> roles = roleService.listRoles(userName);
        String str="";
        List<RolePermission> rolePermissions = new ArrayList<>();

        for (Role role : roles) {
            RolePermissionExample example = new RolePermissionExample();
            example.createCriteria().andRidEqualTo(role.getRid());
            List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
            rolePermissions.addAll(rps);
        }

        for (RolePermission rolePermission : rolePermissions) {
            Permission p = permissionMapper.selectByPrimaryKey(rolePermission.getPid());
            if(p.getUrl()!=null){
                str+=p.getUrl()+",";
            }
        }
        List<String> list1 = Arrays.asList(StringUtils.split(str, ","));//由Arrays.asList() 返回的市Arrays的内部类ArrayList， 而不是java.util.ArrayList。
        List<String> list = new ArrayList<>(list1);
        for(String a:list)
        {
            result.add(a);
        }
        return result;
    }

    @CacheEvict(value="Permission", allEntries=true)
    public void add(Permission u) {
        permissionMapper.insert(u);
    }

    @CacheEvict(value="Permission", allEntries=true)
    public void delete(Integer id)
    {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value="Permission", allEntries=true)
    public void update(Permission u) {
        permissionMapper.updateByPrimaryKeySelective(u);
    }

    @Cacheable(value="Permission",key = "'get'+'-'+#id")
    public Permission get(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    public List<Permission> list() {
        PermissionExample example = new PermissionExample();
        example.setOrderByClause("pid desc");
        return permissionMapper.selectByExample(example);

    }

    public List<Permission> list(Role role) {
        List<Permission> result = new ArrayList<>();
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRidEqualTo(role.getRid());
        List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
        for (RolePermission rolePermission : rps) {
            result.add(permissionMapper.selectByPrimaryKey(rolePermission.getPid()));
        }

        return result;
    }


    public boolean needInterceptor(String requestURI) { //判断url是否需要拦截
        List<Permission> ps = list();
        String str="";
        for (Permission p : ps) {
            if(p.getUrl()!=null)
            {
                    str+=p.getUrl()+",";
            }
            //由Arrays.asList() 返回的市Arrays的内部类ArrayList， 而不是java.util.ArrayList。
            List<String> list1 = Arrays.asList(StringUtils.split(str, ","));
            List<String> list = new ArrayList<>(list1);
            for(String a:list)
            {
                if (a.equals(requestURI)){
                    System.out.println("url在权限中存在");
                    return true;
                }
            }
        }
        System.out.println("url在权限中不存在");
        return false;
    }


    public Set<String> listPermissionURLs(String userName) {  //获取某角色拥有的url
        Set<String> result = new HashSet<>();
        List<Role> roles = roleService.listRoles(userName);
        String str="";
        List<RolePermission> rolePermissions = new ArrayList<>();

        for (Role role : roles) {
            RolePermissionExample example = new RolePermissionExample();
            example.createCriteria().andRidEqualTo(role.getRid());
            List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
            rolePermissions.addAll(rps);
        }

        for (RolePermission rolePermission : rolePermissions) {
            Permission p = permissionMapper.selectByPrimaryKey(rolePermission.getPid());
            if(p.getUrl()!=null){
                str+=p.getUrl()+",";
            }
        }
        //由Arrays.asList() 返回的市Arrays的内部类ArrayList， 而不是java.util.ArrayList。
        List<String> list1 = Arrays.asList(StringUtils.split(str, ","));
        List<String> list = new ArrayList<>(list1);
        for(String a:list)
        {
            result.add(a);
        }
        System.out.println(result);
        return result;
    }

    public List<Permission> getmenuByuserid(String roledesc) {
        System.out.println("session中："+roledesc);
        RoleExample example = new RoleExample();
        example.createCriteria().andDescEqualTo(roledesc);
        List<Role> rps = roleMapper.selectByExample(example);
        return permissionMapper.getmenuByuserid(rps.get(0).getRid());
    }

    @Cacheable(value="Permission",key = "'list'+'-'+#start+'-'+#size+'-'+#role.name")
    public Msg list(int start, int size, Role role) {
        Map<String, PageInfo<Permission>> permission_list = new HashMap<>();
        PageHelper.startPage(start, size);
        List<Permission> ps = list();
        List<Permission> currentPermissions = list(role);
        PageInfo<Permission> page1 = new PageInfo<>(ps);
        PageInfo<Permission> page2 = new PageInfo<>(currentPermissions);
        System.out.println("客户"+page1.getPageSize());
        permission_list.put("all_permission", page1);   //全部权限（用于展示）---->拆开
        permission_list.put("role_permission", page2);  //该角色的权限（用于默认选中）
        return new Msg(permission_list);
    }
}
