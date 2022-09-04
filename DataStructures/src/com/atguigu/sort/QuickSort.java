package com.atguigu.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author Peter
 * @date 2022/8/23 17:45
 * @description 快速排序
 */

public class QuickSort {

    public static void main(String[] args) {
        //int[] arr = {-9, 78, 0, 23, -567, 70, 1, 10, 6};
        //System.out.println("arr = " + Arrays.toString(arr));
        //quickSort(arr, 0, arr.length - 1);
        //System.out.println("arr = " + Arrays.toString(arr));


        int size = 10;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 8000);
        }
        System.out.println("arr = " + Arrays.toString(array));

        LocalDateTime now = LocalDateTime.now();
        System.out.println("排序前时间: " + now);

        quickSort(array, 0, array.length - 1);

        LocalDateTime end = LocalDateTime.now();
        System.out.println("排序后时间: " + end);

        System.out.println("arr = " + Arrays.toString(array));

    }


    /**
     * TODO 有bug
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int[] arr, int left, int right) {
        int le = left;
        int ri = right;

        int pivot = arr[(left + right) / 2];
        int temp = 0;

        while (le < ri) {

            //在 pivot的左边一直找,找到大于等于 pivot值,才退出
            while (arr[le] < pivot) {
                le += 1;
            }

            //在 pivot的右边一直找,找到小于等于 pivot值,才退出
            while (arr[ri] > pivot) {
                ri -= 1;
            }

            //如果 l >= r说明 pivot的左右两的值，已经按照左边全部是
            //小于等于 pivot值，右边全部是大于等于 pivot值
            if (le >= ri) {
                break;
            }

            temp = arr[le];
            arr[le] = arr[ri];
            arr[ri] = temp;

            //如果交换完后，发现这个 arr[l] == pivot值 相等 r--， 前移
            if (arr[le] == pivot) {
                ri -= 1;
            }

            //如果交换完后，发现这个 arr[r] == pivot值 相等 l++， 后移
            if (arr[ri] == pivot) {
                le += 1;
            }

            //如果 l == r,必须 l++, r--,否则为出现栈溢出
            if (le == ri) {
                le += 1;
                ri -= 1;
            }

            //向左递归
            if (left < ri) {
                quickSort(arr, left, ri);
            }

            //向右递归
            if (right > le) {
                quickSort(arr, le, right);
            }
        }

    }


}
