package zhbit.za102.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zhbit.za102.bean.Location;
import zhbit.za102.bean.Msg;
import zhbit.za102.service.LocationService;

import java.util.List;

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
}
