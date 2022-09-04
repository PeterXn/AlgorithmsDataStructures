package com.atguigu.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author Peter
 * @date 2022/8/24 10:42
 * @description Usage
 * 基数排序(桶排序)介绍:
 * <p>
 * 基数排序（radix sort）属于“分配式排序”（distribution sort），又称“桶子法”（bucket sort）或bin sort，
 * 顾名思义，它是通过键值的各个位的值，将要排序的元素分配至某些“桶”中，达到排序的作用
 * 基数排序法是属于稳定性的排序，基数排序法的是效率高的稳定性排序法
 * <p>
 * 基数排序(Radix Sort)是桶排序的扩展
 * 基数排序是1887年赫尔曼·何乐礼发明的。它是这样实现的：将整数按位数切割成不同的数字，然后按每个位数分别比较。
 */

public class RadixSort {

    public static void main(String[] args) {
        //int[] arr = {53, 3, 542, 748, 14, 214};
        //System.out.println("原数据: " + Arrays.toString(arr));
        //radixSort(arr);

        int size = 8000000;
        int[] arr = new int[size];
        int[] temp = new int[arr.length];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        //System.out.println("arr = " + Arrays.toString(arr));

        LocalDateTime now = LocalDateTime.now();
        System.out.println("排序前时间: " + now);

        radixSort(arr);

        LocalDateTime end = LocalDateTime.now();
        System.out.println("排序后时间: " + end);

        //System.out.println("arr = " + Arrays.toString(arr));

    }

    /**
     * 基数排序，空间换时间的算法
     */
    public static void radixSort(int[] arr) {
        //1.得到数组中最大的数位
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = String.valueOf(max).length();
        System.out.println("maxLength = " + maxLength);

        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];
        int index = 0;

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //第1轮排序,对每个元素的个位数排序处理
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位数
                int digitOfElement = arr[j] / n % 10;
                //放到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            index = 0;
            //遍历每一桶，并将桶中是数据，放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们才取出放到原数据组
                if (bucketElementCounts[k] != 0) {
                    for (int x = 0; x < bucketElementCounts[k]; x++) {
                        //取出元素放入arr
                        arr[index] = bucket[k][x];
                        index++;
                    }
                }

                //第 i+1轮处理后，需要将每个 bucketElementCounts[k] = 0！！！！
                bucketElementCounts[k] = 0;
            }

            //System.out.println("第" + (i + 1) + "轮排序后的数据: " + Arrays.toString(arr));
        }

    }

    /**
     * 推演基数排序，空间换时间的算法
     */
    public static void radixSortByStep(int[] arr) {

        //定义一个二维数组，表示 10个桶,每个桶就是一个一维数组
        //说明
        //1.二维数组包含 10个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为 arr.length
        //3.名明确，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //可以这里理解
        //比如：bucketElementCounts[0] ,记录的就是 bucket[0]桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        //第1轮排序,对每个元素的个位数排序处理
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位数
            int digitOfElement = arr[j] % 10;
            //放到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        int index = 0;
        //遍历每一桶，并将桶中是数据，放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，我们才取出放到原数据组
            if (bucketElementCounts[k] != 0) {
                for (int x = 0; x < bucketElementCounts[k]; x++) {
                    //取出元素放入arr
                    arr[index] = bucket[k][x];
                    index++;
                }
            }

            //第 i+1轮处理后，需要将每个 bucketElementCounts[k] = 0！！！！
            bucketElementCounts[k] = 0;
        }

        System.out.println("第1轮排序后的数据: " + Arrays.toString(arr));


        //第1轮排序,对每个元素的个位数排序处理
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位数
            int digitOfElement = arr[j] / 10 % 10;
            //放到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        //遍历每一桶，并将桶中是数据，放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，我们才取出放到原数据组
            if (bucketElementCounts[k] != 0) {
                for (int x = 0; x < bucketElementCounts[k]; x++) {
                    //取出元素放入arr
                    arr[index] = bucket[k][x];
                    index++;
                }
            }

            //第 i+1轮处理后，需要将每个 bucketElementCounts[k] = 0！！！！
            bucketElementCounts[k] = 0;
        }

        System.out.println("第1轮排序后的数据: " + Arrays.toString(arr));


        //第1轮排序,对每个元素的个位数排序处理
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位数
            int digitOfElement = arr[j] / 100 % 10;
            //放到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        //遍历每一桶，并将桶中是数据，放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，我们才取出放到原数据组
            if (bucketElementCounts[k] != 0) {
                for (int x = 0; x < bucketElementCounts[k]; x++) {
                    //取出元素放入arr
                    arr[index] = bucket[k][x];
                    index++;
                }
            }

            //第 i+1轮处理后，需要将每个 bucketElementCounts[k] = 0！！！！
            bucketElementCounts[k] = 0;
        }

        System.out.println("第1轮排序后的数据: " + Arrays.toString(arr));


    }


}
