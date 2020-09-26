package zhbit.za102.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhbit.za102.bean.Msg;
import zhbit.za102.bean.Permission;
import zhbit.za102.bean.Role;
import zhbit.za102.service.PermissionService;
import zhbit.za102.service.RolePermissionService;
import zhbit.za102.service.RoleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    RolePermissionService rolePermissionService;
    @Autowired
    PermissionService permissionService;

    @GetMapping("/listRole")
    public Msg list() {
        try {
            List<Role> rs = roleService.list();
            return new Msg(rs);
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("查询失败", 401);
        }
    }

    @GetMapping("/listRolePermission")
    public Msg listRolePermission() {
        try {
            List<Role> rs = roleService.list();
            Map<Role, List<Permission>> role_permissions = new HashMap<>();

            for (Role role : rs) {
                List<Permission> ps = permissionService.list(role);
                role_permissions.put(role, ps);
            }
            return new Msg(role_permissions);
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("查询失败", 401);
        }
    }

    @GetMapping("/editRole")
    public Msg editRole(@RequestParam("rid") Integer rid,@RequestParam(value = "start",defaultValue = "1")int start,
                        @RequestParam(value = "size",defaultValue = "8")int size)throws Exception {
        try {
            System.out.println(size);
            Role role = roleService.get(rid);
            PageHelper.startPage(start, size);
            //List<Map> resMap = new ArrayList<Map>();
            Map<String, PageInfo<Permission>> permission_list = new HashMap<>();

            List<Permission> ps = permissionService.list();
            List<Permission> currentPermissions = permissionService.list(role);
            PageInfo<Permission> page1 = new PageInfo<>(ps);
            PageInfo<Permission> page2 = new PageInfo<>(currentPermissions);
            System.out.println("客户"+page1.getPageSize());
            permission_list.put("all_permission", page1);   //全部权限（用于展示）---->拆开
            permission_list.put("role_permission", page2);  //该角色的权限（用于默认选中）
            //resMap.add(permission_list);
            return new Msg(permission_list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("查询指定角色失败", 401);
        }
    }

    @DeleteMapping("deleteRole")
    public Msg delete(@RequestParam("rid") Integer rid) {
        try {
            roleService.delete(rid);
            return new Msg();
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("删除指定角色失败", 401);
        }
    }

    @PutMapping("updateRole")
    public Msg update(@RequestBody Role role, @RequestParam("permissionIds") Integer[] permissionIds) {
        try {
            rolePermissionService.setPermissions(role, permissionIds);
            roleService.update(role);
            return new Msg();
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("更新指定角色失败", 401);
        }
    }

    @PostMapping("addRole")
    public Msg add(@RequestBody Role role) {
        try {
            roleService.add(role);
            return new Msg();
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("添加指定角色信息失败", 401);
        }
    }
}
