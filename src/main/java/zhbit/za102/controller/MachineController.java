package zhbit.za102.controller;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zhbit.za102.bean.Machine;
import zhbit.za102.bean.Msg;
import zhbit.za102.service.MachineService;
import com.github.pagehelper.PageInfo;
import java.util.List;


@RestController
public class MachineController {
    @Autowired
    MachineService machineService;

    @GetMapping("/listmachine")
    public Msg list(@RequestParam(value = "start",defaultValue = "1")int start,
                    @RequestParam(value = "size",defaultValue = "8")int size)throws Exception {
        try {
            return machineService.list(start, size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("查询失败", 401);
        }
    }
}
