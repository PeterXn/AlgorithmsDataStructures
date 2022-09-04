package com.atguigu.search;

import java.util.Arrays;

/**
 * @author Peter
 * @date 2022/8/24 16:49
 * @description Usage
 * 斐波那契(黄金分割法)查找基本介绍:
 * <p>
 * 黄金分割点是指把一条线段分割为两部分，使其中一部分与全长之比等于另一部分与这部分之比。取其前三位数字的近似值是0.618。
 * 由于按此比例设计的造型十分美丽，因此称为黄金分割，也称为中外比。这是一个神奇的数字，会带来意向不大的效果。
 * 斐波那契数列 {1, 1, 2, 3, 5, 8, 13, 21, 34, 55 } 发现斐波那契数列的两个相邻数 的比例，无限接近 黄金分割值0.618
 */

public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        //int[] fib = fib();
        //System.out.println("Arrays.toString(fib) = " + Arrays.toString(fib));

        int i = fibSearch(arr, 1234);
        System.out.println("下标为 i = " + i);

    }

    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f;
    }

    public static int fibSearch(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length - 1;
        //表示斐波那契分割数值的下标
        int k = 0;
        int mid = 0;
        int[] fib = fib();

        while (hi > fib[k] - 1) {
            k++;
        }

        //因为 f[k]值 可能大于 a的 长度，因此我们需要使用 Arrays类，构造一个新的数组，并指向 temp[]
        //不足的部分会使用 0填充
        int[] temp = Arrays.copyOf(arr, fib[k]);
        //实际上需求使用 a数组最后的数填充 temp
        for (int i = hi + 1; i < temp.length; i++) {
            temp[i] = arr[hi];
        }

        //使用 while来循环处理，找到我们的数 key
        while (lo <= hi) {
            mid = lo + fib[k - 1] - 1;
            if (key < temp[mid]) {
                //我们应该继续向数组的前面查找(左边)
                hi = mid - 1;
                //1.全部元素 =前面的元素 +后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即 在 f[k-1]的前面继续查找 k--
                //即下次循环 mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) {
                //我们应该继续向数组的后面查找(右边)
                lo = mid + 1;
                //1.全部元素 =前面的元素 +后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3.因为后面我们有 f[k-2]所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                //4.即在 f[k-2]的前面进行查找 k -=2
                //5.即下次循环 mid = f[k - 1 - 2] - 1
                k -= 2;
            } else {
                //找到目标值
                if (mid < hi) {
                    return mid;
                } else {
                    return hi;
                }
            }
        }

        return -1;
    }
}
