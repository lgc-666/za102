package zhbit.za102.Utils;

import Jama.Matrix;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zhbit.za102.bean.Class;
import zhbit.za102.bean.Machine;
import zhbit.za102.bean.StopVisit;
import zhbit.za102.bean.Visit;
import zhbit.za102.service.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class DataUtil {
    @Resource
    RedisUtils redisUtil;
    @Autowired
    VisitService visitService;
    @Autowired
    StopVisitService stopVisitService;
    @Autowired
    ClassDataService classDataService;
    @Autowired
    ClassService classService;
    @Autowired
    MachineService machineService;
    @Autowired
    LocationService locationService;

    Map<String,Object> macMap;
    Map<String,Object> submacMap;

    List<Map<String,Object>> stu2=new ArrayList();
    public List<List<Map<String,Object>>> stu3=new ArrayList();

    double totalWeight = 0;

    public List<List<Map<String, Object>>> getStu3() {
        return stu3;
    }

    Timestamp dt;
    /**
     * 检查Mac是否已经存在过，看普通区域和禁止区域这2个访问数据表里是否有这个mac,且mac只有一个
     * @param mac
     * @return
     */
    public  boolean checkExist(String mac,String address){
        //先看缓存有没有，没有就到数据库去找，数据库有就放入缓存
        if ((redisUtil.hget("visit",mac+'-'+address)!=null)||(redisUtil.hget("stopvisit",mac+'-'+address)!=null))
            return true;
        else{
            List<Visit> visits = visitService.findvisitByMac(mac);
            List<StopVisit> stopvisits = stopVisitService.findstopVisitByMac(mac);
            if (visits.isEmpty()||stopvisits.isEmpty())
                return false;  //数据库中没有才给在进程中插入new_student值
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
    public void cacheMac(String address, Integer inJudge, Date in_time, Date left_time, String rt, Integer visited_times, Date beat, Date last_in_time, String mac, Integer rssi){
        macMap = new HashMap<>();
        submacMap = new HashMap<>();
        submacMap.put("mac",mac);
        submacMap.put("rssi",rssi);
        submacMap.put("address",address);
        submacMap.put("inJudge",inJudge);
        submacMap.put("in_time",in_time);
        submacMap.put("left_time",left_time);
        submacMap.put("rt",rt);
        submacMap.put("visited_times",visited_times);
        submacMap.put("beat",beat);
        submacMap.put("last_in_time",last_in_time);
        macMap.put(mac+'-'+address,submacMap);
        redisUtil.hmset("visit",macMap);
    }
    public void cacheStopMac(String address, Integer inJudge, Date in_time, Date left_time, String rt, Integer visited_times, Date beat, Integer handleJudge, String mac, Integer rssi){
        macMap = new HashMap<>();
        submacMap = new HashMap<>();
        submacMap.put("mac",mac);
        submacMap.put("rssi",rssi);
        submacMap.put("address",address);
        submacMap.put("inJudge",inJudge);
        submacMap.put("in_time",in_time);
        submacMap.put("left_time",left_time);
        submacMap.put("rt",rt);
        submacMap.put("visited_times",visited_times);
        submacMap.put("beat",beat);
        submacMap.put("handleJudge",handleJudge);
        macMap.put(mac+'-'+address,submacMap);
        redisUtil.hmset("stopvisit",macMap);
    }

    //向普通区域表插数值
    public boolean insertMac(String address, Integer inJudge, Integer visited_times, String mac, Integer rssi){
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        //String dt = df.format(new Date());// 获取当前系统时间并格式化
        dt = new Timestamp(System.currentTimeMillis());
        Visit u = new Visit();
        u.setMac(mac);
        u.setRssi(rssi);
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
        cacheMac(address,inJudge,dt,dt,"0",visited_times,dt,dt,mac,rssi);
        return true;
    }
    //向禁止区域表插数值
    public boolean insertStopMac(String address, Integer inJudge, Integer visited_times, String mac, Integer rssi){
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        //String dt = df.format(new Date());// 获取当前系统时间并格式化
        dt = new Timestamp(System.currentTimeMillis());
        StopVisit u = new StopVisit();
        u.setMac(mac);
        u.setRssi(rssi);
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
        cacheStopMac(address,inJudge,dt,dt,"0",visited_times,dt,0,mac,rssi);
        return true;
    }

    //初始化区域数据统计表：针对tomcat关闭2小时后启动和tomcat关闭一下又启动的场景
    public boolean initClassData(){
        if (classDataService.selectWithin1hour()==0){  //当前时间和更新时间的差不在1小时内则插入（大于1小时）
            insertClassData();
            return true;
        }else{ //如果差值在1小时内则更新
            Calendar c = Calendar.getInstance();
            Integer hours = c.get(Calendar.HOUR_OF_DAY);  //HOUR_OF_DAY指24小时制
            List<String> addressList = classService.findAllClass();
            classDataService.updateWithin1hour(hours,addressList.size());
        }
        return true;
    }

    //向数据统计表插数值
    public boolean insertClassData(){
        Calendar c = Calendar.getInstance();  //获取当前的时间
        int hours = c.get(Calendar.HOUR_OF_DAY);
        List<String> addressList = classService.findAllClass();
        //有多少个区域就插入多少条数据
        for (String address:addressList) {
            classDataService.insertClassData(address,hours);
        }
        return true;
    }

    //获取普通区域的相应mac的用户信息（先找缓存，缓存没有则找数据库并插入到缓存）
    public Map<String,Object> getMacMap(String mac,String address)
    {
        if (redisUtil.hget("visit",mac+'-'+address)!=null)
            return (Map)redisUtil.hget("visit",mac+'-'+address);
        else{
            List<Visit> visits = visitService.findvisitByMac(mac);
            cacheMac(visits.get(0).getAddress(),visits.get(0).getInjudge(),visits.get(0).getInTime(),visits.get(0).getLeftTime(),visits.get(0).getRt(),visits.get(0).getVisitedTimes(),visits.get(0).getBeat(),visits.get(0).getLastInTime(),visits.get(0).getMac(),visits.get(0).getRssi());
            return (Map)redisUtil.hget("visit",mac+'-'+address);
        }
    }
    //获取禁止区域的相应mac的用户信息（先找缓存，缓存没有则找数据库并插入到缓存）
    public Map<String,Object> getStopMacMap(String mac,String address)
    {
        if (redisUtil.hget("stopvisit",mac+'-'+address)!=null)
            return (Map)redisUtil.hget("stopvisit",mac+'-'+address);
        else{
            List<StopVisit> stopvisits = stopVisitService.findstopVisitByMac(mac);
            cacheStopMac(stopvisits.get(0).getAddress(),stopvisits.get(0).getInjudge(),stopvisits.get(0).getInTime(),stopvisits.get(0).getLeftTime(),stopvisits.get(0).getRt(),stopvisits.get(0).getVisitedTimes(),stopvisits.get(0).getBeat(),stopvisits.get(0).getHandlejudge(),stopvisits.get(0).getMac(),stopvisits.get(0).getRssi());
            return (Map)redisUtil.hget("stopvisit",mac+'-'+address);
        }
    }

    //更新普通区域的缓存信息
    public void refreshMacCache(String mac,String address,Map macMap){
        redisUtil.hset("visit",mac+'-'+address,macMap);
    }
    //更新禁止区域的缓存信息
    public void refreshStopMacCache(String mac,String address,Map stopmacMap){
        redisUtil.hset("stopvisit",mac+'-'+address,stopmacMap);
    }

    //设备缓存初始化（把已在后台管理系统添加了的设备放到缓存中）
    public void refreshMachineCache(){
        List<Machine> machineList = machineService.listAll();
        Map<String,Object> machineMap = new HashMap<>();
        for (Machine machine:machineList) {
            Map<String,Object> subMachineMap = new HashMap<>();
            subMachineMap.put("machineId",machine.getMachineid());
            subMachineMap.put("address",machine.getAdress());
            subMachineMap.put("status",machine.getStatus());
            subMachineMap.put("leastRssi",machine.getLeastrssi());
            subMachineMap.put("beat",machine.getBeat());
            subMachineMap.put("x",machine.getX());
            subMachineMap.put("y",machine.getY());
            machineMap.put(machine.getMachineid(),subMachineMap);
        }
        redisUtil.del("machineAP");
        redisUtil.hmset("machineAP",machineMap);
        System.out.println(redisUtil.hget("machineAP","101"));
    }

    public void get(String machineid){
        System.out.println("测试："+redisUtil.hget("machineAP",machineid));
    }

    public Object getmachineAP(String machineid){
        return redisUtil.hget("machineAP",machineid);
    }

    //刷新设备缓存心跳
    public void refreshMachineCacheBeat(String machineId){
        System.out.println(machineId);
        System.out.println(getmachineAP(machineId));
        Map<String,Object> subMachineMap = (Map<String,Object>)getmachineAP(machineId);
        System.out.println(subMachineMap);
        subMachineMap.put("beat",new Timestamp(System.currentTimeMillis()));
        redisUtil.hset("machineAP",machineId,subMachineMap);
    }

    public int getDayDiffer(Date startDate, Date endDate) throws ParseException {
        //判断是否跨年
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String startYear = yearFormat.format(startDate);
        String endYear = yearFormat.format(endDate);
        if (startYear.equals(endYear)) {
            /*  使用Calendar跨年的情况会出现问题    */
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            int startDay = calendar.get(Calendar.DAY_OF_YEAR);
            calendar.setTime(endDate);
            int endDay = calendar.get(Calendar.DAY_OF_YEAR);
            return endDay - startDay;
        } else {
            /*  跨年不会出现问题，需要注意不满24小时情况（2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0）  */
            //  只格式化日期，消除不满24小时影响
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long startDateTime = dateFormat.parse(dateFormat.format(startDate)).getTime();
            long endDateTime = dateFormat.parse(dateFormat.format(endDate)).getTime();
            return (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24));
        }
    }

    /**
     * 排列组合：用到了递归
     * @param shu  元素
     * @param targ  要选多少个元素
     * @param has   当前有多少个元素
     * @param cur   当前选到的下标
     *
     * 1    2   3     //开始下标到2
     * 1    2   4     //然后从3开始
     */
    public void reSort(List<Map<String,Object>> shu, int targ, int has, int cur) {
            if(has == targ) {
                List<Map<String,Object>>stu4=new ArrayList();
                System.out.println(stu2);
                stu4.addAll(stu2);
                stu3.add(stu4);
                return;
            }

            for(int i=cur;i<shu.size();i++) {
                if(!stu2.contains(shu.get(i))) {
                    stu2.add(shu.get(i));
                    reSort(shu, targ, has+1, i);
                    stu2.remove(stu2.size()-1);  //删除最后一个元素
                }
            }
    }

    /**利用RSSI得到设备距AP的距离(单位：米)**/
    //A为距离探测设备1m时的rssi值的绝对值，最佳范围在45-49之间
    //n为环境衰减因子，需要测试矫正，最佳范围在3.25-4.5之间
    public double GetDisFromRSSI(Integer rssi)
    {
        int A=45;
        double n=3.5;
        double rawDis = 0.0;
        double power = (-A - rssi) / (10 * n);
        double height = 5.0;  //高度补偿值
        rawDis = Math.pow(10, power);  //公式(pow是次方根)
        //返回AP到UE的距离d（空间的）
        System.out.println(rawDis+"米");
        //return rawDis;
        if(rawDis<5.0){
            rawDis=5.0;
        }
        double d=Math.sqrt(Math.pow(rawDis, 2) - Math.pow(height, 2));
        d=Double.valueOf(String.format("%.1f", d)); //保留1位小数
        //返回AP到UE的水平面距离
        return d;
    }

    /**基于rssi的加权定位算法实现多个AP定位坐标求解**/
    public Map<String, Double> CaculateByAPList(List<Map<String,Object>> apArray,String mac){ //接收[1, 2, 3]
        Map<String, Double> point=new HashMap<>();
        //创建2个固定长度的2维数组
        double[][] a_array = new double[2][2];
        double[][] b_array = new double[2][1];

        //距离数组(定义3维数组)
        double[] distanceArray = new double[3];
        for(int i = 0; i< 3; i++)  //3点定位
        {
            distanceArray[i] = GetDisFromRSSI((Integer) redisUtil.hget(mac,apArray.get(i).get("machineId").toString()));
            System.out.println("3点定位："+distanceArray[i]);
        }

        /**
        //3台机器间的3个距离d1、d2、d3
        double AP1X=0.0;
        double AP2X=0.0;
        double AP3X=0.0;
        double AP1Y=0.0;
        double AP2Y=0.0;
        double AP3Y=0.0;
        List<Machine> APs = machineService.list();
        for(Machine c:APs){
            if(c.getMachineid()==apArray.get(0).get("machineId").toString()){
                AP1X=Double.valueOf(c.getX());
                AP1Y=Double.valueOf(c.getY());
            }
            else if(c.getMachineid()==apArray.get(1).get("machineId").toString()){
                AP2X=Double.valueOf(c.getX());
                AP2Y=Double.valueOf(c.getY());
            }
            else (c.getMachineid()==apArray.get(2).get("machineId").toString()){
                AP3X=Double.valueOf(c.getX());
                AP3Y=Double.valueOf(c.getY());
            }
        }
        double d1= Math.sqrt(Math.pow(AP2X-AP1X, 2) + Math.pow(AP2Y-AP1Y, 2));

        **/


        //系数矩阵A初始化
        for (int i = 0; i<2; i++)
        {
               a_array[i][0] = 2 * ((Double.valueOf(apArray.get(i).get("x").toString())) -(Double.valueOf(apArray.get(2).get("x").toString())));
                System.out.println("矩阵数组："+a_array[i][0]);
                a_array[i][1] = 2 * ((Double.valueOf(apArray.get(i).get("y").toString())) -(Double.valueOf(apArray.get(2).get("y").toString())));
       }

        //矩阵b初始化
        for (int i = 0; i< 2; i++)
        {
            b_array[i][0] = Math.pow(Double.valueOf(apArray.get(i).get("x").toString()), 2)
                    - Math.pow(Double.valueOf(apArray.get(2).get("x").toString()), 2)
                    + Math.pow(Double.valueOf(apArray.get(i).get("y").toString()), 2)
                    - Math.pow(Double.valueOf(apArray.get(2).get("y").toString()), 2)
                    + Math.pow(distanceArray[2], 2)
                    - Math.pow(distanceArray[i], 2);
        }

        //将数组转换成Matrix类型  new Matrix() 要求必须是double类型
        Matrix A = new Matrix(a_array);
        Matrix B = new Matrix(b_array);
        //计算 X=(A^T * A)^-1 * A^T * b
        Matrix a1 = A.transpose(); // A的转置:A^T
        Matrix a2 = a1.times(A); //A^T * A
        Matrix a3 = a2.inverse().times(a1); //(A^T * A)^-1 * A^T
        Matrix resultX = a3.times(B); //(A^T * A)^-1 * A^T * b
        //Matrix resultX =A.inverse().times(B);
        double[][] res = resultX.getArray();
/*        double[][] res = a3.getArray();
        double[][] res2= new double[2][2];
        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                res2[i][j] = res[i][j] * b_array[i][0];
            }
        }*/
        /*对应的权值*/
        double weight = 0;
        for (int i = 0; i < 3; i++)
        {
            weight += (1.0 / distanceArray[i]);
        }

        weight=Double.valueOf(String.format("%.1f", weight));
        System.out.println("weight："+weight);
        totalWeight += weight;
        System.out.println("totalWeight："+totalWeight);
        point.put("x",Double.valueOf(String.format("%.1f", res[0][0]*weight)));
        point.put("y",Double.valueOf(String.format("%.1f", res[1][0]*weight)));
        System.out.println("point："+point);
        return point;
    }


    //计算加权后的坐标
    public Map<String, Integer>cpoint(Double totalX,Double totalY){
        Map<String, Integer> point=new HashMap<>();
        System.out.println("加权值："+totalWeight);
        Double x=totalX/totalWeight;
        Double y=totalY/totalWeight;
        //double转integer：先转化成int类型，再转Integer
        int x1= (int)Math.round(x); //        Math.round(x)四舍五入
        int y1=(int)Math.round(y);
        Integer x2=Integer.valueOf(x1);
        Integer y2=Integer.valueOf(y1);
        point.put("macx",x2);
        point.put("macy",y2);
        System.out.println("加权后的point："+point);
        totalWeight = 0;//一定要归0否则会一直叠加
        return  point;
    }

    //根据区域x、y的范围值，判断在哪个区域,MinX-->MaxX,MinY-->MaxY
    public String judgeClass(Integer x,Integer y){
        String atAdress = null;
        Integer MinX=null;
        Integer MaxX=null;
        Integer MinY=null;
        Integer MaxY=null;
        List<Class> classes = classService.list();
        for(Class c:classes){
            List<String> list1 = Arrays.asList(StringUtils.split(c.getX1(), ","));
            List<String> list2 = Arrays.asList(StringUtils.split(c.getX2(), ","));
            List<String> list3 = Arrays.asList(StringUtils.split(c.getY1(), ","));
            List<String> listX1 = new ArrayList<>(list1);
            List<String> listX2 = new ArrayList<>(list2);
            List<String> listY1 = new ArrayList<>(list3);
            MinX=Integer.valueOf(listX1.get(0));
            MinY=Integer.valueOf(listX1.get(1));
            MaxX=Integer.valueOf(listX2.get(0));
            MaxY=Integer.valueOf(listY1.get(1));
            if(MinX<x && x<MaxX && MinY<y && y<MaxY){
                atAdress=c.getAdress();
            }
        }
        return atAdress;
    }

    //根据区域名，判断是否为禁止区域
    public Integer Stopjudge(String addressname){
        return classService.listbyaddress(addressname).get(0).getStopjudge();
    }

    //人的位置记录

}
