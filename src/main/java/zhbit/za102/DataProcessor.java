package zhbit.za102;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import zhbit.za102.Utils.DataUtil;
import zhbit.za102.Utils.RedisUtils;

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
    private Map<String,Object> customerMap;
    private Integer timeCount;//计算离开时间
    private Timestamp latest_time;//当前时间戳
    private Integer visited_times;//访问次数
    private Integer rssiJ;
    private String address;

    //AP数量
    private Integer APcount = 0;
    //新客人
    private Integer newIn = 0;
    //人流量
    private Integer walker = 0;
    //小时进店量
    private Integer hour_in_customer = 0;
    //客流量
    private Integer customer=0;
    //跳出量
    private Integer jumpOut = 0;


    @Autowired
    private RedisUtils redisUtil;
    @Autowired
    private DataUtil dataUtil;


    @Override
    public void run(String... args) throws Exception {
        try {
            //监听器
            ds = new DatagramSocket(PORT);
            System.out.println("等待链接");
            buf = new byte[1024];
            dp = new DatagramPacket(buf, buf.length);
            //初始化设备表区域数据表，将表数据放缓存中
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
                        if (jsonObject!=null){
                            //把数据初步分析出来
                            machineId = jsonObject.getString("Id").toString();
                            data = jsonObject.getString("Data");
                            if (data != null) {
                                jsonArray = JSONArray.parseArray(data);
                            } else
                                continue;
                            //如果设备在后台管理系统没有被添加,则不接受处理
                            if (machineId.matches(idM)&&redisUtil.hget("machineAp",machineId)!=null){
                                //更新设备beat;
                                dataUtil.refreshMachineCacheBeat(machineId);
                                System.out.println("进来进行处理");
                                dataSize = jsonArray.size();/**分析各个mac的数据，拆分data值**/
                                APcount+=1;
                                for (int i = 0; i < dataSize; i++){
                                    jsonObjectData = jsonArray.getJSONObject(i);
                                    mac = jsonObjectData.getString("mac");
                                    rssi = jsonObjectData.getInteger("rssi");
                                    //筛选
                                    if (mac != null && mac.matches(macM) && rssi != null && rssi.toString().matches(rssiM)&&!mac.startsWith("00:00")&&rssi>(Integer)((Map)redisUtil.hget("machineAp",machineId)).get("leastRssi")) {
                                        System.out.println("进来了");
                                        //设置mac-machine-rssi缓存（key--项--值）：再次插入的缓存中当key、项相同时会把值覆盖掉（当人不断发生位移时，同一个AP收到同一个人的rssi信号每次都会不同的场景）。HSET过去只能设置一个键值对，如果需要一次设置多个，则必须使用HMSET（M表示多重）
                                        redisUtil.hset(mac,machineId,rssi);
                                    }
                                    else
                                        continue;
                                    if(APcount<=3||APcount==0||redisUtil.hmget(mac).size()<=3){  //AP<=3或缓存中的键值对<=3
                                        continue;  //继续循环
                                    }
                                    else{ //该mac至少有3个rssi了
                                        /**开始计算（x,y）**/
                                        //取mac缓存中rssi信号最强的前4个计算
                                        Map<Object,Object> map=redisUtil.hmget(mac);  //由项返回多个键值对
                                        List<Map.Entry<String, Integer>> list = new LinkedList(map.entrySet());
                                        // 下面的也可以写成lambda表达式这种形式：Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
                                        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                                            @Override
                                            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                                                return o2.getValue().compareTo(o1.getValue()); // 这里改为根据value值进行降序排序(加个负号)，这里也可以改成根据key和value进行排序
                                            }
                                        });
                                        System.out.println("取前四条数据,并存value");
                                        List<Map<String,Object>> aplist = new ArrayList<>();
                                        for (Map.Entry<String, Integer> p : list.subList(0, 4)) {  //key：设备id，值：rssi
                                            System.out.println(p.getKey() + " " + p.getValue());
                                            aplist.add((Map)redisUtil.hget("machineAp",p.getKey()));//返回的是value
                                        }
                                        //进行4选3排列AP组合
                                        dataUtil.reSort(aplist,3,0,0);
                                        List<List<Map<String,Object>>> sortResult=dataUtil.getStu3();
                                        Double totalX=0.0;
                                        Double totalY=0.0;
                                        Map<String, Double> respoint=new HashMap<>();
                                        Map<String, Integer> macpoint=new HashMap<>();
                                        for(List<Map<String,Object>> sort:sortResult){ //[[1, 2, 3], [1, 2, 4], [1, 3, 4], [2, 3, 4]]
                                            respoint=dataUtil.CaculateByAPList(sort,mac);  //传[1, 2, 3]
                                            totalX+=respoint.get("x");
                                            totalY+=respoint.get("y");
                                        }
                                        macpoint=dataUtil.cpoint(totalX,totalY);
                                        macpoint.get("macx");
                                        macpoint.get("macy");
                                    }
                                }
                            }
                        }
                    }catch(Exception e){
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
}
