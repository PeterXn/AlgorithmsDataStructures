package com.atguigu.dac;

/**
 * @author Peter
 * @date 2022/8/31 17:03
 * @description 汉诺塔
 */

public class Hanoitower {
    public static void main(String[] args) {

        hanoiTower(10, 'A', 'B', 'C');

    }

    public static void hanoiTower(int num, char a, char b, char c) {
        //只有1个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + " -> " + c);
        } else {
            //先最把上面的所有盘A->B,移动过程会使用到C
            hanoiTower(num - 1, a, c, b);
            //把最下面的盘从A移动到C
            System.out.println("第" + num + "个盘从 " + a + " -> " + c);
            //把B塔上的所有盘从B->C，移动过程乃至A塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
