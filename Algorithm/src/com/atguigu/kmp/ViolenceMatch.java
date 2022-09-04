package com.atguigu.kmp;

/**
 * @author Peter
 * @date 2022/9/1 0:18
 * @description 查找s1在s2中出现的最早的位置
 */

public class ViolenceMatch {
    public static void main(String[] args) {
        String str = "abcdefghixiaojklmnopqrstxiaocheng";
        String dst = "xiaoca";
        int i = violenceMatch(str, dst);
        System.out.println("i = " + i);
    }

    /**
     * 暴力匹配算法
     */
    public static int violenceMatch(String str, String dst) {

        char[] s1 = str.toCharArray();
        char[] d1 = dst.toCharArray();

        int s1Len = s1.length;
        int d1Len = d1.length;

        int i = 0;
        int j = 0;
        while (i < s1Len && j < d1Len) {
            if (s1[i] == d1[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }

        //判断匹配成功
        if (j == d1Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
