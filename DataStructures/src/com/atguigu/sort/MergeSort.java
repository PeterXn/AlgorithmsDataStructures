package com.atguigu.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author Peter
 * @date 2022/8/24 0:39
 * @description Usage
 */

public class MergeSort {

    public static void main(String[] args) {
        //int[] arr = {8, 4, 5, 7, 1, 3, 6, 2, 11, 0, -11};
        //int[] temp = new int[arr.length];
        //mergeSort(arr, 0, arr.length - 1, temp);
        //System.out.println("arr = " + Arrays.toString(arr));

        int size = 80000;
        int[] arr = new int[size];
        int[] temp = new int[arr.length];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 8000);
        }
        //System.out.println("arr = " + Arrays.toString(arr));

        LocalDateTime now = LocalDateTime.now();
        System.out.println("排序前时间: " + now);

        mergeSort(arr, 0, arr.length - 1, temp);

        LocalDateTime end = LocalDateTime.now();
        System.out.println("排序后时间: " + end);

        //System.out.println("arr = " + Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        /**
         * 排序前时间: 2022-08-24T01:13:15.316
         * 排序后时间: 2022-08-24T01:13:15.331
         */
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        //(一)
        //先把左右两边(有序)的数据按照规则填充到 temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
                //即将左边的当前元素，填充到 temp数组
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                //反之,将右边有序序列的当前元素，填充到 temp数组
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //(二)
        //把有剩余数据的一边的数据依次全部填充到 temp
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }


        //(三)
        //将 temp数组的元素拷贝到 arr
        //注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
