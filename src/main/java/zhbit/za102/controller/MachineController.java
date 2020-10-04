package zhbit.za102.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhbit.za102.bean.Machine;
import zhbit.za102.bean.Msg;
import zhbit.za102.service.MachineService;

import java.text.SimpleDateFormat;
import java.util.Date;


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

    @DeleteMapping("/deletemachine")
    public Msg delete(@RequestParam("mid") Integer mid) {
        try {
            machineService.delete(mid);
            return new Msg("删除操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("删除操作失败", 401);
        }
    }

    @PutMapping("/updatemachine")
    public Msg update(@RequestParam("mid") Integer mid, @RequestParam("adress") String adress, @RequestParam("machineid") String machineid, @RequestParam("status") String status, @RequestParam("leastRssi") Integer leastRssi, @RequestParam("beat") String beat, @RequestParam("x") String x, @RequestParam("y") String y) {
        try {
            Machine c=machineService.get(mid);
            c.setAdress(adress);
            c.setMachineid(machineid);
            //String转成Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(beat);
            c.setBeat(date);
            if(leastRssi!=null){
                c.setLeastrssi(leastRssi);
            }
            c.setX(x);
            c.setY(y);
            c.setStatus(status);
            machineService.update(c);
            return new Msg("修改操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("修改操作失败", 401);
        }
    }

    @PostMapping("/addmachine")
    public Msg add(@RequestParam("adress") String adress, @RequestParam("machineid") String machineid, @RequestParam("status") String status, @RequestParam("leastRssi") Integer leastRssi, @RequestParam("beat") String beat, @RequestParam("x") String x, @RequestParam("y") String y) {
        try {
            Machine c=new Machine();
            c.setAdress(adress);
            c.setMachineid(machineid);
            //String转成Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(beat);
            c.setBeat(date);
            c.setLeastrssi(leastRssi);
            c.setX(x);
            c.setY(y);
            c.setStatus(status);
            machineService.add(c);
            return new Msg("新增操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("新增操作失败", 401);
        }
    }

}
