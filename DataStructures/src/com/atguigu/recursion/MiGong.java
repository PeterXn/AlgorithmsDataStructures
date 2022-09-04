package com.atguigu.recursion;

/**
 * @author Peter
 * @date 2022/8/22 15:49
 * @description 迷宫问题
 */

public class MiGong {

    public static void main(String[] args) {
        //二维数组，模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙，2表已通路径，3表示已走过
        //上、下、左、右的最外围都是墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("输出地图的情况：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        /**
         * 1 1 1 1 1 1 1
         * 1 0 0 0 0 0 1
         * 1 0 0 0 0 0 1
         * 1 1 1 0 0 0 1
         * 1 0 0 0 0 0 1
         * 1 0 0 0 0 0 1
         * 1 0 0 0 0 0 1
         * 1 1 1 1 1 1 1
         */

        //setWay(map, 1, 1);
        setNewWay(map, 1, 1);
        System.out.println("标志过的地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }


    }

    /**
     //使用递归回溯来给小球找路
     //说明
     //1. map表示地图
     //2. i,j表示从地图的哪个位置开始出发 (1,1)
     //3.如果小球能到 map[6][5]位置，则说明通路找到.
     //4.约定： 当 map[i][j]为 0表示该点没有走过 当为 1表示墙 ； 2表示通路可以走 ； 3表示该点已经走过，但是走不通
     //5.在走迷宫时，需要确定一个策略(方法)下->右->上->左 ,如果该点走不通，再回溯
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                //按照策略 下->右->上->左 走
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    //3表示这路不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //map[i][j] = 1,2,3
                return false;
            }
        }
    }

    /**
     * 修改找路的策略，改成 上->右->下->左
     */
    public static boolean setNewWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                //按照策略 上->右->下->左
                if (setNewWay(map, i - 1, j)) {
                    return true;
                } else if (setNewWay(map, i, j + 1)) {
                    return true;
                } else if (setNewWay(map, i + 1, j)) {
                    return true;
                } else if (setNewWay(map, i, j - 1)) {
                    return true;
                } else {
                    //3表示这路不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //map[i][j] = 1,2,3
                return false;
            }
        }
    }


}
