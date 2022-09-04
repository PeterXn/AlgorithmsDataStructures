package com.atguigu.tree;

import java.time.LocalDateTime;

/**
 * @author Peter
 * @date 2022/8/26 15:12
 * @description 堆排序
 * TODO review
 */

public class HeapSort {

    public static void main(String[] args) {
        int size = 2000000;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 80000);
        }

        LocalDateTime now = LocalDateTime.now();
        System.out.println("排序前时间: " + now);

        heapSort(array);

        LocalDateTime end = LocalDateTime.now();
        System.out.println("排序后时间: " + end);
        //System.out.println("第2次(array) = " + Arrays.toString(array));
    }

    /**
     *
     * @param arr 排序的数组
     */
    public static void heapSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序");
        for (int i = arr.length / 2 -1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        /**
         * 2).将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
         * 3).重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换
         * 步骤，直到整个序列有序。
         */
        for (int j = arr.length - 1; j >= 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        //System.out.println("第2次(arr) = " + Arrays.toString(arr));

    }

    /**
     * 将一个数组转换成大项堆（升序）
     * *功能： 完成 将 以 i对应的非叶子结点的树调整成大顶堆
     * *举例 int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap =>得到 {4, 9, 8, 5, 6}
     * *如果我们再次调用 adjustHeap传入的是 i = 0 =>得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
     * @param arr 待调整的数组
     * @param i 非叶子节点在数组中的位置
     * @param length 表示对多少个元素继续调整，length是在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        //  k = i * 2 + 1 k是 i结点的左子结点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //如果子结点大于父结点
            if (arr[k] > temp) {
                //把较大的值赋给当前结点
                arr[i] = arr[k];
                //!!! i指向 k,继续循环比较
                i = k;
            } else {
                break;
            }
        }
        //当 for循环结束后，我们已经将以 i为父结点的树的最大值，放在了 最顶(局部)
        arr[i] = temp;
    }

}
