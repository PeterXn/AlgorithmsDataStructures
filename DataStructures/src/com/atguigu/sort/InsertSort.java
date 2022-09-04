package com.atguigu.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author Peter
 * @date 2022/8/23 11:19
 * @description Usage
 */

public class InsertSort {
    public static void main(String[] args) {
        int size = 80000;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 80000);
        }

        LocalDateTime now = LocalDateTime.now();
        System.out.println("排序前时间: " + now);

        insertSort(array);

        LocalDateTime end = LocalDateTime.now();
        System.out.println("排序后时间: " + end);

    }

    public static void insertSort(int[] arr) {
        /**
         * 排序前时间: 2022-08-23T14:45:26.891
         * 排序后时间: 2022-08-23T14:45:27.841
         */
        for (int i = 1; i < arr.length; i++) {

            int insertValue = arr[i];
            int insertIndex = i - 1;

            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }
            //System.out.println("第" + i + "轮插入：" + Arrays.toString(arr));
        }
    }

    public static void insertSortByStep(int[] arr) {
        //第1轮
        int insertValue = arr[1];
        int insertIndex = 1 - 1;

        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        arr[insertIndex + 1] = insertValue;
        System.out.println("第1轮插入：" + Arrays.toString(arr));


        //第2轮
        insertValue = arr[2];
        insertIndex = 2 - 1;

        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        arr[insertIndex + 1] = insertValue;
        System.out.println("第2轮插入：" + Arrays.toString(arr));

        //第3轮
        insertValue = arr[3];
        insertIndex = 3 - 1;

        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        arr[insertIndex + 1] = insertValue;
        System.out.println("第3轮插入：" + Arrays.toString(arr));

    }
}
