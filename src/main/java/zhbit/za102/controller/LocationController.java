package zhbit.za102.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zhbit.za102.bean.Location;
import zhbit.za102.bean.Msg;
import zhbit.za102.service.LocationService;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LocationController {
    @Autowired
    LocationService locationService;

    @GetMapping("/listLocation")
    public Msg list(@RequestParam(value = "start",defaultValue = "1")int start,
                    @RequestParam(value = "size",defaultValue = "8")int size)throws Exception {  //所有用户
        try {
            return locationService.list(start, size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("查询失败", 401);
        }
    }

    @GetMapping("/getDBlocation")
    public Msg getDBlocation(){  //所有用户
        try {
            return new Msg(locationService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("查询失败", 401);
        }
    }

    @GetMapping("/getDBlocationNotRepeat")
    public Msg getDBlocation2(){  //所有用户
        try {
            List<String> listmac=locationService.searchLocationMac();
            List<Location> listlocation = new ArrayList<>();
            for(String mac:listmac){
                listlocation.add((Location) locationService.searchLocationleatMac(mac));
            }
            return new Msg(listlocation);
        } catch (Exception e) {
            e.printStackTrace();
            return new Msg("查询失败", 401);
        }
    }
}
