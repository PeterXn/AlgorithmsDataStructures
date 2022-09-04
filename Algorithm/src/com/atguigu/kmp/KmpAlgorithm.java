package com.atguigu.kmp;

import java.util.Arrays;

/**
 * @author Peter
 * @date 2022/9/1 11:00
 * @description Usage
 */

public class KmpAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] as = kmpNext("ABCDABD");
        System.out.println("Arrays.toString(as) = " + Arrays.toString(as));
        int i = kmpSearch(str1, str2);
        System.out.println("i = " + i);
    }

    public static int kmpSearch(String str, String dst) {
        int[] next = kmpNext(dst);

        for (int i = 0, j = 0; i < str.length(); i++) {

            //处理str.charAt(i) != dst.charAt(j)，去调整j的大小
            while (j > 0 && str.charAt(i) != dst.charAt(j)) {
                j = next[j - 1];
            }

            if (str.charAt(i) == dst.charAt(j)) {
                j++;
            }
            if (j == dst.length()) {
                //找到目标字符串
                return i - j + 1;
            }
        }

        return -1;
    }

    /**
     * 获取匹配串的部分匹配表
     */
    public static int[] kmpNext(String dst) {
        int[] next = new int[dst.length()];
        //如果dst长度为1,部分匹配值为0
        next[0] =0;
        for (int i = 1, j = 0; i < dst.length(); i++) {
            while (j > 0 && dst.charAt(i) != dst.charAt(j)) {
                j = next[j - 1];
            }

            if (dst.charAt(i) == dst.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        return next;
    }
}
