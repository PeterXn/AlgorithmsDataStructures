package com.atguigu.search;

import java.util.ArrayList;

/**
 * @author Peter
 * @date 2022/8/24 15:01
 * @description Usage
 */

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 4, 7, 11, 23, 23, 23, 23, 45, 67, 111, 1234};
        int i = binarySearch(arr, 0, arr.length - 1, 52);
        System.out.println("i = " + i);

        ArrayList<Integer> integers = binarySearchs(arr, 0, arr.length - 1, 23);
        System.out.println("integers = " + integers);

    }


    // Arr必须是有序的
    public static int binarySearch(int[] arr, int left, int right, int findValue) {
        //当left > right，说明这个数组没有当前查找的值
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (findValue > midValue) {
            //向右查找
            return binarySearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            //向左查找
            return binarySearch(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }

    /**
     * 课后思考题： {1,8, 10, 89, 1000, 1000，1234}
     * 当一个有序数组中，有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000.
     */
    public static ArrayList<Integer> binarySearchs(int[] arr, int left, int right, int findValue) {
        //当left > right，说明这个数组没有当前查找的值
        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (findValue > midValue) {
            //向右查找
            return binarySearchs(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            //向左查找
            return binarySearchs(arr, left, mid - 1, findValue);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                //向左查找
                if (temp < 0 || arr[temp] != findValue) {
                    break;
                }
                list.add(temp);
                temp--;
            }

            list.add(mid);

            temp = mid + 1;
            while (true) {
                //向右查找
                if (temp > arr.length - 1 || arr[temp] != findValue) {
                    break;
                }
                list.add(temp);
                temp++;
            }

            return list;
        }
    }
}
