package com.atguigu.linkedlist;

/**
 * @author Peter
 * @date 2022/8/21 0:19
 * @description Josephu(约瑟夫 、 约瑟夫环)  问题
 * Josephu  问题为：设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，
 * 数到m 的那个人出列，它的下一位又从1开始报数，
 * 数到m的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
 */

public class Josephu {

    public static void main(String[] args) {
        //测试一把看看构建环形链表，和遍历是否 ok
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(5);
        list.showBoy();
        // 2->4->1->5->3
        list.countBoy(1, 2, 5);

    }

}

/**
 * 环形单向列表
 */
class CircleSingleLinkedList {
    /**
     * 创建一个 first节点,当前没有编号
     */
    private Boy first = null;

    /**
     * 添加节点，构成一个环形链表
     *
     * @param nums
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }

        Boy curBoy = null;
        //构造环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，添加节点
            Boy boy = new Boy(i);
            //如果是第一个节点
            if (i == 1) {
                first = boy;
                first.setNext(first);
                //让 curBoy指向第一个小孩
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                //遍历结束
                break;
            }
            curBoy = curBoy.getNext();
        }
    }


    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }

        Boy helper = first;
        // helper指向环形链表的最后一个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }

        //小孩报数前，先让 first和 helper移动 startNo - 1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //当小孩报数时，让 first和 helper指针同时 的移动 countNum - 1次,然后出圈
        while (true) {
            if (helper == first) {
                //圈中只有一个节点时
                break;
            }
            //让 first和 helper指针同时 的移动 countNum - 1次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            //这时 first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩 %d 出圈\n", first.getNo());
            //这时将 first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号 %d \n", first.getNo());
    }
}

/**
 * 表示一个节点
 */
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
