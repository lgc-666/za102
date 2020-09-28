package zhbit.za102.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhbit.za102.bean.Msg;
import zhbit.za102.bean.Role;
import zhbit.za102.bean.User;
import zhbit.za102.service.RoleService;
import zhbit.za102.service.UserRoleService;
import zhbit.za102.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/listUser")
    public Msg list(@RequestParam(value = "start",defaultValue = "1")int start,
                    @RequestParam(value = "size",defaultValue = "8")int size)throws Exception {  //所有用户
        try {
            return userService.list(start, size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("查询失败", 401);
        }
    }

    @GetMapping("/editUser")
    public Msg edit(@RequestParam("uid") Integer uid) {  //指定其中一个用户
        try {
            Map<String, List<Role>> role_list = new HashMap<>();
            List<Role> rs = roleService.list();
            User user = userService.get(uid);
            List<Role> roles = roleService.listRoles(user);
            role_list.put("all_role", rs);   //全部角色
            role_list.put("user_role", roles);  //用户的角色
            return new Msg(role_list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("查询指定用户失败", 401);
        }
    }

    @DeleteMapping("/deleteUser")
    public Msg delete(@RequestParam("uid") Integer uid) {
        try {
            userService.delete(uid);
            return new Msg();
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("删除指定用户失败", 401);
        }
    }

    @PutMapping("/updateUser")
    public Msg update(@RequestParam("uid") Integer uid, @RequestParam("roleIds") Integer[] roleIds) { //更改用户权限(传数组)
        try {
            User user=userService.get(uid);
            userRoleService.setRoles(user, roleIds);
            return new Msg("更改账号权限成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("更改账号权限失败", 401);
        }
    }

    @PutMapping("/updatePassword")
    public Msg updatePassword(@RequestParam("newpassword") String newpassword, @RequestParam("uid") Integer uid) { //更改密码
        try {
            User user=userService.get(uid);
            System.out.println(newpassword+"----"+user.getUsername());
            // 如果在修改的时候没有设置密码，就表示不改动密码,改了密码要重新加盐生成新的加密密钥
            if (newpassword.length() != 0) {
                String salt = new SecureRandomNumberGenerator().nextBytes().toString();
                int times = 2;  //2次加密
                String algorithmName = "md5";
                String encodedPassword = new SimpleHash(algorithmName, newpassword, salt, times).toString();
                user.setSalt(salt);
                user.setPassword(encodedPassword);
                userService.update(user);
                return new Msg("该账号密码已更改");
            } else {
                //user.setPassword(null); //所调用的更新方法只更新不为null的属性
                return new Msg("密码更改失败，输入密码不能为空");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("更新账号密码失败", 401);
        }
    }

    @PostMapping("/addUser")
    public Msg add(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            String algorithmName = "md5";
            String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();

            User u = new User();
            u.setUsername(username);
            u.setPassword(encodedPassword);
            u.setSalt(salt);
            userService.add(u);
            return new Msg();
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("添加指定用户信息失败", 401);
        }
    }

}
