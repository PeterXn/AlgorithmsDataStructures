package com.atguigu.search;

/**
 * @author Peter
 * @date 2022/8/24 16:25
 * @description Usage
 */

public class InsertValueSearch {
    static int count = 0;

    public static void main(String[] args) {

        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        int valueSearch = insertValueSearch(arr, 0, arr.length - 1, 521);
        System.out.println("valueSearch = " + valueSearch);
        System.out.println("count = " + count);
    }


    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        count++;
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -1;
        }

        //求出mid
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findValue > midVal) {
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else if (findValue < midVal) {
            return insertValueSearch(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }

    }
}
