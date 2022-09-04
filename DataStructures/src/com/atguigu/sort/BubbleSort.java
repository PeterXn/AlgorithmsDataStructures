package com.atguigu.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author Peter
 * @date 2022/8/23 1:16
 * @description Usage
 */

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        //int arr[] = {-2, -1, 3, 9, 10};
        //showBubbleSortByStep(arr);
        System.out.println("showBubbleSort**************************");
        //showBubbleSort(arr);
        System.out.println("optimizeBubbleSort**************************");

        int[] array = new int[80000];
        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 80000);
        }

        LocalDateTime now = LocalDateTime.now();
        System.out.println("排序前时间: " + now);

        optimizeBubbleSort(array);

        LocalDateTime end = LocalDateTime.now();
        System.out.println("排序后时间: " + end);
    }

    public static void optimizeBubbleSort(int[] arr) {
        /**
         * 排序前时间: 2022-08-23T09:36:42.999
         * 排序后时间: 2022-08-23T09:36:56.434
         */
        int temp = 0;
        boolean flag = false;
        //第一趟排序
        for (int inx = 0; inx < arr.length - 1; inx++) {
            for (int i = 0; i < arr.length - 1 - inx; i++) {
                //如果前面的数大于后面的数，则交换
                if (arr[i] > arr[i + 1]) {
                    flag = true;
                    temp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = temp;
                }
            }
            //System.out.println("第" + (inx + 1) + "趟排序后：" + Arrays.toString(arr));

            //设置标志
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

    public static void showBubbleSort(int[] arr) {
        int temp = 0;
        //第一趟排序
        for (int inx = 0; inx < arr.length - 1; inx++) {
            for (int i = 0; i < arr.length - 1 - inx; i++) {
                //如果前面的数大于后面的数，则交换
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = temp;
                }
            }
            System.out.println("第" + (inx + 1) + "趟排序后：" + Arrays.toString(arr));
        }
    }

    public static void showBubbleSortByStep(int[] arr) {
        //int arr[] = {3, 9, -1, 10, -2};

        /**
         * 演示排序的过程
         */

        int temp = 0;
        //第一趟排序
        for (int i = 0; i < arr.length - 1; i++) {
            //如果前面的数大于后面的数，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
            }
        }
        System.out.println("第一趟排序后：" + Arrays.toString(arr));

        //第二趟排序
        for (int i = 0; i < arr.length - 2; i++) {
            //如果前面的数大于后面的数，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
            }
        }
        System.out.println("第二趟排序后：" + Arrays.toString(arr));

        //第三趟排序
        for (int i = 0; i < arr.length - 3; i++) {
            //如果前面的数大于后面的数，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
            }
        }
        System.out.println("第三趟排序后：" + Arrays.toString(arr));

        //第四趟排序
        for (int i = 0; i < arr.length - 4; i++) {
            //如果前面的数大于后面的数，则交换
            if (arr[i] > arr[i + 1]) {
                temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
            }
        }
        System.out.println("第四趟排序后：" + Arrays.toString(arr));
    }

}
