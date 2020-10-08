package zhbit.za102;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import zhbit.za102.Utils.DataUtil;
import zhbit.za102.Utils.RedisUtils;
import zhbit.za102.dao.ClassDataMapper;
import zhbit.za102.dao.StopVisitMapper;
import zhbit.za102.dao.VisitMapper;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Timestamp;
import java.util.*;

@Component
public class DataProcessor implements CommandLineRunner {
    //监听器端口（全局常量）
    private final static int PORT = 3020;
    private DatagramSocket ds;
    private DatagramPacket dp;
    private byte[] buf = null;
    private String strReceive;

    //解析json数据
    private JSONObject jsonObject = null;
    private JSONObject jsonObjectData = null;
    private JSONArray jsonArray = null;

    //正则表达式筛选数据
    private String macM = "([a-f0-9A-F]{2}:){5}[a-f0-9A-F]{2}";
    private String rssiM = "-[1-9]\\d*";
    private String idM = "^[1-9]\\d*$";

    //数据处理
    private String machineId;
    private String mac;
    private Integer rssi;
    private String data;  //发送过来的信息中所收到的data属性值
    private Integer dataSize; //有几个mac行
    private Map<String, Object> macMap;
    private Integer timeCount;//计算离开时间
    private Timestamp latest_time;//当前时间戳
    private Integer visited_times;//访问次数
    private Integer StopJudege;  //所在区域是否为禁止区域
    private String atAddress;  //所在区域

    //AP数量
    private Integer APcount = 0;

    //新客人
    private Integer new_student = 0;
    //进入区域人数
    private Integer in_class_number = 0;
    //当前区域人数
    private Integer class_now_number = 0;
    //小时进入区域数量
    private Integer hour_in_class_number = 0;

    @Autowired
    RedisUtils redisUtil;
    @Autowired
    DataUtil dataUtil;
    @Autowired
    ClassDataMapper classDataMapper;
    @Autowired
    VisitMapper visitMapper;
    @Autowired
    StopVisitMapper stopVisitMapper;

    @Override
    public void run(String... args) throws Exception {
        try {
            //监听器
            ds = new DatagramSocket(PORT);
            System.out.println("等待链接");
            buf = new byte[1024];
            dp = new DatagramPacket(buf, buf.length);
            //初始化设备表区域数据表，将表数据放缓存中（特别注意初始化后只有4条，想要后续每小时插入4条进行数据统计，就要用到异步线程的定时操作）
            dataUtil.refreshMachineCache();
            dataUtil.initClassData();

            while (true) {
                //synchronized关键字是用来控制线程同步的，就是在多线程的环境下，控制synchronized代码段不被多个线程同时执行
                synchronized (this) {
                    try {
                        //获取wifi探针数据
                        ds.receive(dp);
                        strReceive = new String(dp.getData(), 0, dp.getLength());
                        //转成json对象取值
                        jsonObject = JSONObject.parseObject(strReceive);
                        if (jsonObject != null) {
                            //把数据初步分析出来
                            machineId = jsonObject.getString("Id").toString();
                            data = jsonObject.getString("Data");
                            if (data != null) {
                                jsonArray = JSONArray.parseArray(data);
                            } else
                                continue;
                            //如果设备在后台管理系统没有被添加,则不接受处理
                            if (machineId.matches(idM) && redisUtil.hget("machineAp", machineId) != null) {
                                //更新设备beat;
                                dataUtil.refreshMachineCacheBeat(machineId);
                                System.out.println("进来进行处理");
                                dataSize = jsonArray.size();/**分析各个mac的数据，拆分data值**/
                                APcount += 1;
                                for (int i = 0; i < dataSize; i++) {
                                    jsonObjectData = jsonArray.getJSONObject(i);
                                    mac = jsonObjectData.getString("mac");
                                    rssi = jsonObjectData.getInteger("rssi");
                                    //筛选
                                    if (mac != null && mac.matches(macM) && rssi != null && rssi.toString().matches(rssiM) && !mac.startsWith("00:00") && rssi > (Integer) ((Map) redisUtil.hget("machineAp", machineId)).get("leastRssi")) {
                                        System.out.println("进来了");
                                        //设置mac-machine-rssi缓存（key--项--值）：再次插入的缓存中当key、项相同时会把值覆盖掉（当人不断发生位移时，同一个AP收到同一个人的rssi信号每次都会不同的场景）。HSET过去只能设置一个键值对，如果需要一次设置多个，则必须使用HMSET（M表示多重）
                                        redisUtil.hset(mac, machineId, rssi);
                                    } else
                                        continue;
                                    if (APcount <= 3 || APcount == 0 || redisUtil.hmget(mac).size() <= 3) {  //AP<=3或缓存中的键值对<=3
                                        continue;  //继续循环，跳过本次循环体中余下尚未执行的语句，立即进行下一次的循环条件
                                    } else { //该mac至少有4个rssi了
                                        /**开始计算（x,y）**/
                                        //取mac缓存中rssi信号最强的前4个计算
                                        Map<Object, Object> map = redisUtil.hmget(mac);  //由项返回多个键值对
                                        List<Map.Entry<String, Integer>> list = new LinkedList(map.entrySet());
                                        // 下面的也可以写成lambda表达式这种形式：Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
                                        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                                            @Override
                                            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                                                return o2.getValue().compareTo(o1.getValue()); // 这里改为根据value值进行降序排序(加个负号)，这里也可以改成根据key和value进行排序
                                            }
                                        });
                                        System.out.println("取前四条数据,并存value");
                                        List<Map<String, Object>> aplist = new ArrayList<>();
                                        for (Map.Entry<String, Integer> p : list.subList(0, 4)) {  //key：设备id，值：rssi
                                            System.out.println(p.getKey() + " " + p.getValue());
                                            aplist.add((Map) redisUtil.hget("machineAp", p.getKey()));//返回的是value
                                        }
                                        //进行4选3排列AP组合
                                        dataUtil.reSort(aplist, 3, 0, 0);
                                        List<List<Map<String, Object>>> sortResult = dataUtil.getStu3();
                                        Double totalX = 0.0;
                                        Double totalY = 0.0;
                                        Map<String, Double> respoint = new HashMap<>();
                                        Map<String, Integer> macpoint = new HashMap<>();
                                        for (List<Map<String, Object>> sort : sortResult) { //[[1, 2, 3], [1, 2, 4], [1, 3, 4], [2, 3, 4]]
                                            respoint = dataUtil.CaculateByAPList(sort, mac);  //传[1, 2, 3]
                                            totalX += respoint.get("x");
                                            totalY += respoint.get("y");
                                        }
                                        macpoint = dataUtil.cpoint(totalX, totalY);
                                        //根据区域x、y的范围值，判断在哪个区域
                                        atAddress = dataUtil.judgeClass(macpoint.get("macx"), macpoint.get("macy"));
                                        //根据区域名判断是否为禁止区域
                                        StopJudege = dataUtil.Stopjudge(atAddress);
                                        //当前时间戳
                                        latest_time = new Timestamp(System.currentTimeMillis());
                                        /**先判断是否存在，注意：同一个区域的同一个mac用更新方式，否则插入**/
                                        //不存在的情况
                                        if (!dataUtil.checkExist(mac, atAddress)) {
                                            //新客人
                                            if (StopJudege == 0) {
                                                //加入新客人
                                                dataUtil.insertMac(atAddress, 1, 1, mac, rssi);
                                            } else {
                                                dataUtil.insertStopMac(atAddress, 1, 1, mac, rssi);
                                            }
                                            //加入缓存
                                            new_student++;
                                            in_class_number++;
                                            hour_in_class_number++;
                                        }
                                        //mac在普通区域或禁区已存在的情况
                                        else {
                                            //更新的是人所在区域的mac信息
                                            if (StopJudege == 0) { //所在区域为普通区域
                                                macMap = dataUtil.getMacMap(mac, atAddress);
                                                timeCount = new Long((latest_time.getTime() - (Long) macMap.get("beat")) / (60 * 1000)).intValue();
                                                //出现间隔（AP再次探测到的时间-上次在店心跳）大于5分钟,再进算进入区域量+1（离开5分钟后再进来相当于再次访问，而5分钟内连续访问的不算是再进）
                                                if (timeCount >= 5&&(Integer) macMap.get("inJudge") == 0){  //大于5分钟AP检测不到mac心跳视为之前人跑到了室外，然后再重新跑进来才被检测到
                                                    //上次进店时间为上一次的first_in_time
                                                    macMap.put("last_in_time", (Long) macMap.get("in_time"));
                                                    macMap.put("in_time", latest_time);
                                                    macMap.put("visited_times", (Integer) macMap.get("visited_times") + 1);
                                                    in_class_number++;
                                                    hour_in_class_number++;
                                                }
                                                //出现间隔小于5分钟视为一直在室内（为了方便处理在多个区域间频繁移动的人）
                                                macMap.put("left_time", latest_time);
                                                macMap.put("beat", latest_time);
                                                macMap.put("inJudge", 1);
                                                macMap.put("rssi", rssi);
                                                //更新cache信息
                                                dataUtil.refreshMacCache(mac, atAddress,macMap);

                                            } else { //所在区域为禁区
                                                macMap = dataUtil.getStopMacMap(mac, atAddress);
                                                timeCount = new Long((latest_time.getTime() - (Long) macMap.get("beat")) / (60 * 1000)).intValue();
                                                if (timeCount >= 5&&(Integer) macMap.get("inJudge") == 0) {
                                                    macMap.put("in_time", latest_time);
                                                    macMap.put("visited_times", (Integer) macMap.get("visited_times") + 1);
                                                    in_class_number++;
                                                    hour_in_class_number++;
                                                }
                                                macMap.put("handleJudge", 0);
                                                macMap.put("left_time", latest_time); //离开时间等价于最后一次在店时间latest_in_time
                                                macMap.put("beat", latest_time);
                                                macMap.put("inJudge", 1);
                                                macMap.put("rssi", rssi);
                                                //更新cache信息
                                                dataUtil.refreshStopMacCache(mac,atAddress, macMap);
                                            }
                                        }
                                    }
                                }
                                //这里更新
                                if (new_student != 0 || in_class_number != 0 || hour_in_class_number != 0) {
                                    classDataMapper.updateClassData(atAddress, new_student, in_class_number, hour_in_class_number);//倒序排序更新最新的那条
                                }
                                new_student = 0;
                                in_class_number = 0;
                                hour_in_class_number = 0;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ds.close();
            System.out.println("退出while循环.................");
        }
        System.out.println("退出while循环.................");
    }


    //此进程用于存储跳出量、动态当前客流量和小时客流量
    @Transactional
    @Async
    @Scheduled(cron = "0/5 * * * * ?")
    public void dataThread() {  //5秒一次
        //小时客流量
        Integer subHour_customer = 0;
        //现存人数
        Integer dynamic_customer = 0;
        //跳出量
        Integer jumpOut_customer = 0;

        String subAddress = null;

        Map<String, Object> countExtraMap = new HashMap<>();
        Map<String, Integer> subCountExtraMap;
        Map<Object, Object> subCustomerMap = redisUtil.hmget("visit");
        latest_time = new Timestamp(System.currentTimeMillis());
        //普通区域
        for (Map.Entry<Object, Object> subCustomerMap_1 : subCustomerMap.entrySet()) {
            Object subMac = subCustomerMap_1.getKey();
            Map<String, Object> subCustomerMap_2 = (Map) redisUtil.hget("visit", (String) subMac);
            //时间间隔
            Integer countTime = new Long((latest_time.getTime() - (Long) subCustomerMap_2.get("beat")) / (60 * 1000)).intValue();
            //大于5分钟没有心跳的店内客人
            if (countTime >= 5 && (Integer) subCustomerMap_2.get("inJudge") == 1) {
                Long stayTime = ((Long) subCustomerMap_2.get("left_time") - (Long) subCustomerMap_2.get("first_in_time")) / 1000;
                if (stayTime < 50)  //离开时间（最后一次在店时间）-上次进店小于50秒（进来不到1分钟就走了）
                {
                    jumpOut_customer = 1;  //跳出量+1
                }
                subCustomerMap_2.put("inJudge", 0); //不在区域内
                subCustomerMap_2.put("rt", stayTime.toString()); //停留时间
            }
            else if (countTime < 5 && (Integer) subCustomerMap_2.get("inJudge") == 1) { //人在室内
                dynamic_customer = 1; //现存人数+1
            }
            subAddress = (String)subCustomerMap_2.get("address");
            if (jumpOut_customer!=0||dynamic_customer!=0){
                if (countExtraMap.get(subAddress)==null){
                    subCountExtraMap = new HashMap<>();
                    subCountExtraMap.put("jumpOut_customer",jumpOut_customer);
                    subCountExtraMap.put("dynamic_customer",dynamic_customer);
                    countExtraMap.put(subAddress,subCountExtraMap);
                }else{
                    subCountExtraMap = (Map)countExtraMap.get(subAddress);
                    subCountExtraMap.put("jumpOut_customer",subCountExtraMap.get("jumpOut_customer")+jumpOut_customer);
                    subCountExtraMap.put("dynamic_customer",subCountExtraMap.get("dynamic_customer")+dynamic_customer);
                    countExtraMap.put(subAddress,subCountExtraMap);
                }
            }
            jumpOut_customer = 0;
            dynamic_customer = 0;
            //更新缓存（主要更新inJudge值）
            redisUtil.hset("visit",subMac.toString(),subCustomerMap_2);
        }

        //存到数据库中（遍历上面存储跳出量的map）
        for (Map.Entry<String, Object> subCustomerMap_1:countExtraMap.entrySet()) {
            subAddress = subCustomerMap_1.getKey();
            subCountExtraMap = (Map)subCustomerMap_1.getValue();
            //查询当前小时进店量
            subHour_customer = classDataMapper.searchNowHour_in_customer_number(subAddress);
            //如果当前店面人流量大于小时进店量, 则小时客流量等于当前店面人流量
            if (subCountExtraMap.get("dynamic_customer")>subHour_customer)
                subHour_customer = subCountExtraMap.get("dynamic_customer");
            if (subHour_customer!=0||subCountExtraMap.get("dynamic_customer")!=0||subCountExtraMap.get("jumpOut_customer")!=0)
                classDataMapper.updateDataThread(subAddress,subCountExtraMap.get("dynamic_customer"),subCountExtraMap.get("jumpOut_customer"),subHour_customer);
        }

        //遍历更新设备状态缓存
        Map<Object,Object> machineMap = redisUtil.hmget("machineAp");
        for (Map.Entry<Object, Object> subMachineMap:machineMap.entrySet()) {
            String subMachineId = (String) subMachineMap.getKey();
            Map<String,Object> subMachineMap_1 = (Map)subMachineMap.getValue();
            Integer machineCountTime = new Long((latest_time.getTime() - (Long)subMachineMap_1.get("beat")) / (60*1000)).intValue();
            if (machineCountTime>10)//设备大于10分钟收不到信号则判定为离线
                subMachineMap_1.put("status","离线");
            else
                subMachineMap_1.put("status","在线");
            redisUtil.hset("machineAp",subMachineId,subMachineMap_1);
        }
    }


    //此进程用于存储用户信息和补充跳出量(1小时存一次)
    @Transactional
    @Async
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void saveDataThread(){
        Map<Object,Object> subCustomerMap = redisUtil.hmget("visit");

        for (Map.Entry<Object, Object> subCustomerMap_1:subCustomerMap.entrySet()) {
            String subMac = (String) subCustomerMap_1.getKey();
            Map<String,Object> subCustomerMap_2 = (Map<String, Object>) redisUtil.hget("visit", (String) subMac);
            String mac = subCustomerMap_2.get("mac").toString();
            Timestamp first_in_time = new Timestamp((Long)subCustomerMap_2.get("first_in_time"));
            Timestamp latest_in_time =  new Timestamp((Long)subCustomerMap_2.get("latest_in_time"));
            Timestamp last_in_time =  new Timestamp((Long)subCustomerMap_2.get("last_in_time"));
            Timestamp beat = new Timestamp((Long)subCustomerMap_2.get("beat"));
            visitMapper.updateCustomer(mac,(Integer)subCustomerMap_2.get("rssi"),first_in_time,latest_in_time,beat,(Integer)subCustomerMap_2.get("inJudge"),(Integer)subCustomerMap_2.get("visited_times"),last_in_time,subCustomerMap_2.get("rt"));
        }
        //删除缓存
        redisUtil.del("visit");

        //添加下一个小时的区域时间=======================》数据表小时插入区域的关键
        dataUtil.insertClassData();

        //补充遗漏的跳出量
        List<String> extraJumpOutAddressList = customerMapper.searchExtraJumpOut();
        customerMapper.updateInjudge();
        for (String extraJumpOutAddress:extraJumpOutAddressList)
            shop_dataMapper.updateExtraJumpOut(extraJumpOutAddress);

    }

    //此进程用于删除三个月前的数据
    @Transactional
    @Async
    @Scheduled(cron = "* * * * 1/3 ?")
    public void deleteDataThread(){
        shop_dataMapper.deleteExpiredShop_data();
        customerMapper.deleteExpiredCustomer();
    }

}


