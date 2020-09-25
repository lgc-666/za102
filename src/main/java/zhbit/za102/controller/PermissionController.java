package zhbit.za102.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhbit.za102.bean.Msg;
import zhbit.za102.bean.Permission;
import zhbit.za102.service.PermissionService;
import zhbit.za102.service.RolePermissionService;

import java.util.List;

@RestController
public class PermissionController {
    @Autowired
    PermissionService permissionService;
    @Autowired
    RolePermissionService rolePermissionService;

    @GetMapping("listPermission")
    public Msg list() {
        try {
            List<Permission> ps = permissionService.list();
            return new Msg(ps);
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("查询权限失败", 401);
        }
    }

    @GetMapping("/editPermission")
    public Msg list(@RequestParam("pid") Integer pid) {
        try {
            Permission permission = permissionService.get(pid);
            return new Msg(permission);
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("查询指定权限失败", 401);
        }
    }

    @DeleteMapping("deletePermission")
    public Msg delete(@RequestParam("pid") Integer pid) {
        try {
            permissionService.delete(pid);
            return new Msg();
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("删除指定权限失败", 401);
        }
    }

    @PutMapping("updatePermission")
    public Msg update(@RequestBody Permission permission) {
        try {
            permissionService.update(permission);
            return new Msg();
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("更新指定权限失败", 401);
        }
    }

    @PutMapping("/updateRolePermission")
    public Msg updateRolePermission(@RequestParam("status") String status, @RequestParam("rid") Integer rid, @RequestParam("pid") Integer pid) {
        try {
            rolePermissionService.resetPermission(status,rid,pid);
            return new Msg("更新指定权限成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("更新指定权限失败", 401);
        }
    }

    @RequestMapping("addPermission")
    public Msg add(@RequestBody Permission permission) {
        try {
            permissionService.add(permission);
            return new Msg();
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("增加权限失败", 401);
        }
    }

}
