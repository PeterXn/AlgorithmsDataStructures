package com.atguigu.binarysearchnorecursion;

/**
 * @author Peter
 * @date 2022/8/31 15:59
 * @description 二分查找（非递归）
 */

public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int i = binarySearch(arr, 67);
        System.out.println("i = " + i);

    }


    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
