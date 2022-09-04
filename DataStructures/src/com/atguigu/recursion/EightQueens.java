package com.atguigu.recursion;

/**
 * @author Peter
 * @date 2022/8/22 17:44
 * @description 八皇后问题介绍
 * 八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。该问题是国际西洋棋棋手马克斯·贝瑟尔于1848年提出：
 * 在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，
 * 即：任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 */

public class EightQueens {

    int max = 8;
    int[] array = new int[max];
    static int count = 0;
    static int checkCount = 0;

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.check(0);
        System.out.println("Queens摆放方法：count = " + count);
        System.out.println("checkCount = " + checkCount);
    }

    /**
     * //编写一个方法，放置第 n个皇后
     * //特别注意： check是 每一次递归时，进入到 check中都有 for(int i = 0; i < max; i++)，因此会有回溯
     */
    private void check(int n) {
        if (n == max) {
            printQueens();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            checkCount++;
            //先把当前这个皇后 n ,放到该行的第i列
            array[n] = i;
            //判断当放置第 n个皇后到 i列时，是否冲突
            if (judge(n)) {
                //不冲突
                check(n+1);
            }
        }
    }

    /**
     * //查看当我们放置第 n个皇后,就去检测该皇后是否和前面已经摆放的皇后冲突
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //1. array[i] == array[n]表示判断 第 n个皇后是否和前面的 n-1个皇后在同一列
            //2. Math.abs(n-i) == Math.abs(array[n] - array[i])表示判断第 n个皇后是否和第 i皇后是否在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印queens位置
     */
    private void printQueens() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
