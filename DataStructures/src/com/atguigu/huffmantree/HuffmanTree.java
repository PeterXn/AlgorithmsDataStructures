package com.atguigu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Peter
 * @date 2022/8/27 1:05
 * @description Usage
 */

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);

        preOrder(root);


    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树不能遍历");
        }
    }

    public static Node createHuffmanTree(int[] arr) {
        //遍历arr并排序
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            //排序,升序
            Collections.sort(nodes);

            System.out.println("nodes = " + nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            //从nodes删除处理过的node
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
            System.out.println("nodes = " + nodes);
        }


        //返回root节点
        return nodes.get(0);
    }
}

/**
 * Node实现Comparable接口以排序
 */
class Node implements Comparable<Node> {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //权值比较，升序排序
        return this.value - o.value;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
