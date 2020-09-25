package zhbit.za102.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhbit.za102.bean.Msg;
import zhbit.za102.bean.RegisterApproval;

import zhbit.za102.bean.User;
import zhbit.za102.service.RegisterApprovalService;
import zhbit.za102.service.UserService;

import java.util.List;

@RestController
public class RegisterApprovalController {
    @Autowired
    RegisterApprovalService registerApprovalService;
    @Autowired
    UserService userService;

    @GetMapping("/listregisterApproval")
    public Msg list() {  //所有用户
        try {
            List<RegisterApproval> us = registerApprovalService.list();
            return new Msg(us);
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("查询失败", 401);
        }
    }
    @PostMapping("/agreeregisterApproval")
    public Msg agree(@RequestParam("agreeid") Integer id) {  //所有用户
        try {
            List<RegisterApproval> rs = registerApprovalService.list(id);
            User user = new User();
            user.setUsername(rs.get(0).getUsername());
            user.setPassword(rs.get(0).getPassword());
            user.setSalt(rs.get(0).getSalt());
            user.setMac(rs.get(0).getMac());
            userService.add(user);
            registerApprovalService.delete(id);
            return new Msg("审批已通过");
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("审批操作失败", 401);
        }
    }

    @DeleteMapping("/deleteregisterApproval")
    public Msg delete(@RequestParam("unagreeid") Integer id) {
        try {
            registerApprovalService.delete(id);
            return new Msg("该审批已删除");
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("删除指定用户失败", 401);
        }
    }

}
