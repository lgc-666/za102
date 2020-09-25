package zhbit.za102.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhbit.za102.bean.*;
import zhbit.za102.service.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class LoginController {
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    RegisterApprovalService registerApprovalService;

    @PostMapping("/register")
    public Msg register(@RequestParam("username") String username, @RequestParam("checkPass") String password, @RequestParam("radio") String radio,
                        @RequestParam("mac") String mac) {
        try {
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            String algorithmName = "md5";
            String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();
            Integer rid =Integer.valueOf(radio);
            RegisterApproval u = new RegisterApproval();
            u.setRid(rid);
            u.setUsername(username);
            u.setPassword(encodedPassword);
            u.setSalt(salt);
            List<User> users = userService.list();
            for(User user:users){
                if(user.getMac().equals(mac)){
                    return new Msg("该mac地址已存在",401);
                }
            }
            u.setMac(mac);
            registerApprovalService.add(u);
            return new Msg("注册成功，等待管理员审核");
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("添加用户信息到审批列表失败", 401);
        }
    }

    @PostMapping("/loginUser")
    public Msg login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("radio") String radio, HttpSession httpSession) {
        String str="";
        Map<String,String> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        System.out.println(username);
        System.out.println(password);
        System.out.println(radio);

        Set<String> roles = roleService.listRoleNames(username);
        Iterator<String> it = roles.iterator();
        Integer roleid = null;
        if(radio!=null){
            roleid = Integer.valueOf(radio);
        }
        String rolename = roleService.get(roleid).getName();
        String roledesc = roleService.get(roleid).getDesc();

        System.out.println(roledesc);
        if(!roles.isEmpty()) {  //是否为空
            if(roles.contains(rolename))//是否包含这个角色名
            {
                    httpSession.setAttribute("roledesc",roledesc);
                    str = roledesc;
                //"已记住"和"已认证"是有区别的,并非是认证通过的用户
                //token.setRememberMe(true);
                /*根据http状态码设置code*/
                try {
                    subject.login(token);
                    System.out.println(SecurityUtils.getSubject().getPrincipal());

                } catch (AuthenticationException e){
                    e.printStackTrace();
                    return new Msg("账号或密码错误",401);
                }catch (AuthorizationException e){
                    e.printStackTrace();
                    return new Msg("服务器拒绝执行请求,没有权限",403);
                }
                map.put("username",username);
                map.put("roledesc",str);
                return new Msg(map);
            }
        }
        return new Msg("请选择正确的登录身份!",403);
    }

    @GetMapping("/menu")
    public List<Permission> getmenuByusername(HttpSession httpSession){
           String rolename=(String)httpSession.getAttribute("roledesc");
           return permissionService.getmenuByuserid(rolename);
    }

    @RequestMapping("/login")
    public Msg unlogin(){
        return new Msg("未登录", 401);
    }

    @RequestMapping("/unauthorized")
    public Msg unauthorized(){
        return new Msg("没有权限", 403);
    }
}
