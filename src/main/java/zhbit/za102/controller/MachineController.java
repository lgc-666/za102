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
            //start是当前第几页，size是每页显示几条，设置id倒排序
            PageHelper.startPage(start, size, "mid desc");
            List<Machine> us = machineService.list();
            //分页类处理
            PageInfo<Machine> page = new PageInfo<>(us);
            return new Msg(page);
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("查询失败", 401);
        }
    }
}
