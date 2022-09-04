package com.atguigu.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author Peter
 * @date 2022/8/23 9:46
 * @description 选择排序思想:
 * 选择排序（select sorting）也是一种简单的排序方法。它的基本思想是：
 * 第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，
 * 第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换，
 * 第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换，…，
 * 第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换，…,
 * 第n-1次从arr[n-2]~arr[n-1]中选取最小值，
 * 与arr[n-2]交换，总共通过n-1次，得到一个按排序码从小到大排列的有序序列。
 */

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        //System.out.println("排序前： " + Arrays.toString(arr));
        //selectSortByStep(arr);

        int[] array = new int[80000];
        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 80000);
        }

        LocalDateTime now = LocalDateTime.now();
        System.out.println("排序前时间: " + now);

        selectSort(array);
        //System.out.println("排序后： " + Arrays.toString(arr));

        LocalDateTime end = LocalDateTime.now();
        System.out.println("排序后时间: " + end);
    }

    public static void selectSort(int[] arr) {
        /**
         * 排序前时间: 2022-08-23T10:55:39.717
         * 排序后时间: 2022-08-23T10:55:43.397
         */
        for (int inx = 0; inx < arr.length - 1; inx++) {
            int minIndex = inx;
            int min = arr[inx];
            for (int i = inx + 1; i < arr.length; i++) {
                //找到最小的
                if (min > arr[i]) {
                    min = arr[i];
                    minIndex = i;
                }
            }

            //将最小值放到arr[0]
            if (minIndex != inx) {
                arr[minIndex] = arr[inx];
                arr[inx] = min;
            }
            //System.out.println("第" + (inx + 1) + "轮排序后： " + Arrays.toString(arr));
        }
    }

    public static void selectSortByStep(int[] arr) {
        //第1轮
        int minIndex = 0;
        int min = arr[0];
        for (int i = 0 + 1; i < arr.length; i++) {
            //找到最小的
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }

        //将最小值放到arr[0]
        arr[minIndex] = arr[0];
        arr[0] = min;
        System.out.println("第1轮排序后： " + Arrays.toString(arr));


        //第2轮
        minIndex = 1;
        min = arr[1];
        for (int i = 1 + 1; i < arr.length; i++) {
            //找到最小的
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }

        //将最小值放到arr[0]
        arr[minIndex] = arr[1];
        arr[1] = min;
        System.out.println("第2轮排序后： " + Arrays.toString(arr));

        //第3轮
        minIndex = 2;
        min = arr[2];
        for (int i = 2 + 1; i < arr.length; i++) {
            //找到最小的
            if (min > arr[i]) {
                min = arr[i];
                minIndex = i;
            }
        }

        //将最小值放到arr[0]
        arr[minIndex] = arr[2];
        arr[2] = min;
        System.out.println("第3轮排序后： " + Arrays.toString(arr));

    }
}
