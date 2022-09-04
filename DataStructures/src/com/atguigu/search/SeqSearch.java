package com.atguigu.search;

/**
 * @author Peter
 * @date 2022/8/24 14:41
 * @description 线性查找
 */

public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2, 11};
        int Value = 11;
        int index = seqSearch(arr, Value);
        if (index == -1) {
            System.out.println("没有查找到 Value = " + Value);
        } else {
            System.out.println("查找成功： index = " + index);
        }

    }

    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
