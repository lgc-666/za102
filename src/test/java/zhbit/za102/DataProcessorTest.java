package zhbit.za102;

import org.junit.jupiter.api.Test;

import java.util.*;

class DataProcessorTest {
    List<Integer> list=new ArrayList<>();
    public static Stack<Integer> stack = new Stack<Integer>();
    public static List<Integer> stu=new ArrayList();
    List<Integer> stu2=new ArrayList();
    public static List<List<Integer>> stu3=new ArrayList();


    @Test
    void run() {  //测试倒序排序方法
        Map<Object, Object> map = new TreeMap<>();
        map.put("I am f", -7);
        map.put("I am d", -1);
        map.put("I am b", -3);
        map.put("I am c", -98);
        map.put("I am a", -8);
        map.put("I am e", -2);
        map.put("I am g", -165);
        map.put("I am h", -15);
        List<Map.Entry<String, Integer>> list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue()); // 这里改为根据value值进行降序排序(加个负号)，这里也可以改成根据key和value进行排序
            }
        });
        System.out.println("倒序（大的在前）：");
        for (Map.Entry<String, Integer> i : list) {
            System.out.println(i.getKey() + " " + i.getValue());
        }

        System.out.println("取前四条数据");
        for (Map.Entry<String, Integer> i : list.subList(0, 4)) {
            System.out.println(i.getKey() + " " + i.getValue());
        }
    }
    @Test
    void run2(){
        stu.add(1);
        stu.add(2);
        stu.add(3);
        stu.add(4);
        reSort2(stu,3,0,0);

        System.out.println("随机组合后的值："+stu3);
        System.out.println("取随机组合后的值的第2个："+stu3.get(1));
    }

    public void reSort(List<Integer> shu, int targ, int has, int cur) {

        if(has == targ) {
            System.out.println(stack);
            return;
        }

        for(int i=cur;i<shu.size();i++) {
            if(!stack.contains(shu.get(i))) {
                stack.add(shu.get(i));
                reSort(shu, targ, has+1, i);
                stack.pop(); //删除栈顶值
            }
        }

    }

    public void reSort2(List<Integer> shu, int targ, int has, int cur) {

        if(has == targ) {
            List<Integer>stu4=new ArrayList();
            System.out.println(stu2);
            stu4.addAll(stu2);
            stu3.add(stu4);
            return;
        }

        for(int i=cur;i<shu.size();i++) {
            if(!stu2.contains(shu.get(i))) {
                stu2.add(shu.get(i));
                reSort2(shu, targ, has+1, i);
                stu2.remove(stu2.size()-1);  //删除最后一个元素
            }
        }
    }

}