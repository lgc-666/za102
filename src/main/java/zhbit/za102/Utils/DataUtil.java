package zhbit.za102.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zhbit.za102.bean.StopVisit;
import zhbit.za102.bean.Visit;
import zhbit.za102.service.ClassDataService;
import zhbit.za102.service.StopVisitService;
import zhbit.za102.service.VisitService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class DataUtil {
    @Autowired
    RedisUtils redisUtil;
    @Autowired
    VisitService visitService;
    @Autowired
    StopVisitService stopVisitService;
    @Autowired
    ClassDataService classDataService;

    Map<String,Object> macMap;
    Map<String,Object> submacMap;

    Timestamp dt;
    /**
     * 检查Mac是否已经存在过，看普通区域和禁止区域这2个访问数据表里是否有这个mac,且mac只有一个
     * @param mac
     * @return
     */
    public  boolean checkExist(String mac){
        //先看缓存有没有，没有就到数据库去找，数据库有就放入缓存
        if ((redisUtil.hget("visit",mac)!=null)||(redisUtil.hget("stopvisit",mac)!=null))
            return true;
        else{
            List<Visit> visits = visitService.findvisitByMac(mac);
            List<StopVisit> stopvisits = stopVisitService.findstopVisitByMac(mac);
            if (visits.isEmpty()||stopvisits.isEmpty())
                return false;
            else{
                if(!visits.isEmpty()){
                    cacheMac(visits.get(0).getAddress(),visits.get(0).getInjudge(),visits.get(0).getInTime(),visits.get(0).getLeftTime(),visits.get(0).getRt(),visits.get(0).getVisitedTimes(),visits.get(0).getBeat(),visits.get(0).getLastInTime(),visits.get(0).getMac(),visits.get(0).getRssi());
                }
                if(!stopvisits.isEmpty()){
                    cacheStopMac(stopvisits.get(0).getAddress(),stopvisits.get(0).getInjudge(),stopvisits.get(0).getInTime(),stopvisits.get(0).getLeftTime(),stopvisits.get(0).getRt(),stopvisits.get(0).getVisitedTimes(),stopvisits.get(0).getBeat(),stopvisits.get(0).getHandlejudge(),stopvisits.get(0).getMac(),stopvisits.get(0).getRssi());
                }
                return true;
            }
        }
    }

    /**存AP发来的mac值到缓存(键--项--值)**/
    public void cacheMac(String address, Integer inJudge, Date in_time, Date left_time, String rt, Integer visited_times, Date beat, Date last_in_time, String mac, Integer riss){
        macMap = new HashMap<>();
        submacMap = new HashMap<>();
        submacMap.put("mac",mac);
        submacMap.put("riss",riss);
        submacMap.put("address",address);
        submacMap.put("inJudge",inJudge);
        submacMap.put("in_time",in_time);
        submacMap.put("left_time",left_time);
        submacMap.put("rt",rt);
        submacMap.put("visited_times",visited_times);
        submacMap.put("beat",beat);
        submacMap.put("last_in_time",last_in_time);
        macMap.put(mac,submacMap);
        redisUtil.hmset("visit",macMap);
    }
    public void cacheStopMac(String address, Integer inJudge, Date in_time, Date left_time, String rt, Integer visited_times, Date beat, Integer handleJudge, String mac, Integer riss){
        macMap = new HashMap<>();
        submacMap = new HashMap<>();
        submacMap.put("mac",mac);
        submacMap.put("riss",riss);
        submacMap.put("address",address);
        submacMap.put("inJudge",inJudge);
        submacMap.put("in_time",in_time);
        submacMap.put("left_time",left_time);
        submacMap.put("rt",rt);
        submacMap.put("visited_times",visited_times);
        submacMap.put("beat",beat);
        submacMap.put("handleJudge",handleJudge);
        macMap.put(mac,submacMap);
        redisUtil.hmset("stopvisit",macMap);
    }

    public boolean insertMac(String address, Integer inJudge, Integer visited_times, String mac, Integer riss){
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        //String dt = df.format(new Date());// 获取当前系统时间并格式化
        dt = new Timestamp(System.currentTimeMillis());
        Visit u = new Visit();
        u.setMac(mac);
        u.setRssi(riss);
        u.setAddress(address);
        u.setInjudge(inJudge);
        u.setInTime(dt);
        u.setLeftTime(dt);
        u.setRt("0");
        u.setVisitedTimes(visited_times);
        u.setBeat(dt);
        u.setLastInTime(dt);
        //插入数据库
        visitService.add(u);
        //插入redis缓存
        cacheMac(address,inJudge,dt,dt,"0",visited_times,dt,dt,mac,riss);
        return true;
    }

    public boolean insertStopMac(String address, Integer inJudge, Integer visited_times, String mac, Integer riss){
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        //String dt = df.format(new Date());// 获取当前系统时间并格式化
        dt = new Timestamp(System.currentTimeMillis());
        StopVisit u = new StopVisit();
        u.setMac(mac);
        u.setRssi(riss);
        u.setAddress(address);
        u.setInjudge(inJudge);
        u.setInTime(dt);
        u.setLeftTime(dt);
        u.setRt("0");
        u.setVisitedTimes(visited_times);
        u.setBeat(dt);
        u.setHandlejudge(0);
        //插入数据库
        stopVisitService.add(u);
        //插入redis缓存
        cacheStopMac(address,inJudge,dt,dt,"0",visited_times,dt,0,mac,riss);
        return true;
    }

    //区域数据统计表
    public boolean initclassData(){
        if (classDataService.selectWithin1hour()==0){
            insertShop();
            return true;
        }else{
            Calendar c = Calendar.getInstance();
            Integer hours = c.get(Calendar.HOUR_OF_DAY);
            List<String> addressList = shopMapper.findAllShop();
            shop_dataMapper.updateWithin1hour(hours,addressList.size());
        }
        return true;
    }

}
