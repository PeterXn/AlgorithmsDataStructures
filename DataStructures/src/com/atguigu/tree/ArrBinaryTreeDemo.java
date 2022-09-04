package com.atguigu.tree;

/**
 * @author Peter
 * @date 2022/8/25 23:05
 * @description 数组转换二叉树
 */

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        //1,2,4,5,3,6,7
        arrBinaryTree.preOrder(0);
        System.out.println("**************************");
        arrBinaryTree.infixOrder(0);
        System.out.println("**************************");
        arrBinaryTree.postOrder(0);
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 顺序二叉树的前序遍历
     * @param index 数组下标
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归
        if (index * 2 + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        //向左递归
        if (index * 2 + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按二叉树的中序遍历");
        }
        //向左递归
        if (index * 2 + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归
        if (index * 2 + 2 < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按二叉树的后序遍历");
        }
        //向左递归
        if (index * 2 + 1 < arr.length) {
            postOrder(2 * index + 1);
        }
        //向左递归
        if (index * 2 + 2 < arr.length) {
            postOrder(2 * index + 2);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
    }
}
