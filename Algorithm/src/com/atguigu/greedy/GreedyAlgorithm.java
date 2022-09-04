package com.atguigu.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Peter
 * @date 2022/9/1 17:08
 * @description 贪心算法
 */

public class GreedyAlgorithm {
    public static void main(String[] args) {
        /**
         * 假设存在下面需要付费的广播台，以及广播台信号可以覆盖的地区。 如何选择最少的广播台，让所有的地区都可以接收到信号
         */
        //广播电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //把各个电台放入broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("k1", hashSet1);
        broadcasts.put("k2", hashSet2);
        broadcasts.put("k3", hashSet3);
        broadcasts.put("k4", hashSet4);
        broadcasts.put("k5", hashSet5);

        //存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建ArraysList，存放选择电台的集合
        ArrayList<String> selects = new ArrayList<>();

        //定义临时变量，在遍历过程中，存放遍历中的电台覆盖的地区和当前还没有覆盖的地区的集合
        HashSet<String> tempSet = new HashSet<>();

        String maxKey = null;
        //保存在一次遍历中，能够覆盖最大未覆盖的地区的电台的key
        while (allAreas.size() != 0) {
            maxKey = null;
            //遍历broadcasts
            for (String key : broadcasts.keySet()) {
                tempSet.clear();

                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //两个集合的交集
                tempSet.retainAll(allAreas);

                if (tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的选择结果是： " + selects);
    }
}
