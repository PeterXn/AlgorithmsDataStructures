package com.atguigu.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author Peter
 * @date 2022/8/23 15:15
 * @description 希尔排序法介绍
 * <p>
 * 希尔排序是希尔（Donald Shell）于1959年提出的一种排序算法。
 * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序。
 * <p>
 * 希尔排序法基本思想
 * <p>
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 */

public class ShellSort {
    public static void main(String[] args) {
        int size = 80000;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 80000);
        }

        LocalDateTime now = LocalDateTime.now();
        System.out.println("排序前时间: " + now);

        shellSortOptimize(array);

        LocalDateTime end = LocalDateTime.now();
        System.out.println("排序后时间: " + end);
    }

    public static void shellSortOptimize(int[] arr) {
        /**
         * 排序前时间: 2022-08-23T15:54:03.099
         * 排序后时间: 2022-08-23T15:54:03.131
         */
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //退出while，找到给temp插入的位置
                    arr[j] = temp;
                }
            }
            //System.out.println("第轮排序后： " + Arrays.toString(arr));
        }
    }

    public static void shellSort(int[] arr) {
        /**
         * 排序前时间: 2022-08-23T15:40:53.673
         * 排序后时间: 2022-08-23T15:41:00.973
         */
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素(共 5组，每组有 2个元素),步长 5
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("第" + (++count) + "轮排序后： " + Arrays.toString(arr));
        }
    }

    public static void shellSortByStep(int[] arr) {
        int temp = 0;
        //第1轮，将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素(共 5组，每组有 2个元素),步长 5
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第1轮排序后： " + Arrays.toString(arr));

        //第2轮，将5个数据分成了2组
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素(共 2组，每组有 5个元素),步长 2
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第2轮排序后： " + Arrays.toString(arr));


        //第3轮，将2个数据分成了2组
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素(共 2组，每组有 1个元素),步长 1
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第3轮排序后： " + Arrays.toString(arr));

    }

}
