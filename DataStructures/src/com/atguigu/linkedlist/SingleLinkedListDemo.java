package com.atguigu.linkedlist;

import java.util.Stack;
import java.util.zip.CRC32;

/**
 * @author Peter
 * @date 2022/8/20 0:37
 * @description 单向链表
 */

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);

        singleLinkedList.list();
        System.out.println("**********************************************");
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addByOrder(heroNode1);
        singleLinkedList1.addByOrder(heroNode4);
        singleLinkedList1.addByOrder(heroNode3);
        singleLinkedList1.addByOrder(heroNode2);

        singleLinkedList1.list();

        System.out.println("**********************************************");
        HeroNode heroNode21 = new HeroNode(2, "卢俊义~~", "玉麒麟~~");
        singleLinkedList1.updateByNo(heroNode21);
        singleLinkedList1.list();

        System.out.println("删除后的情况");
        singleLinkedList1.delNodeByNo(1);
        singleLinkedList1.list();
        System.out.println("**********************************************");
        //singleLinkedList1.delNodeByNo(4);
        //singleLinkedList1.list();

        //求单链表中有效节点的个数
        System.out.println("有效节点的个数: " + getLength(singleLinkedList1.getHead()));

        //查找单链表中的倒数第k个结点
        HeroNode res = findLastIndexNode(singleLinkedList1.getHead(), 1);
        System.out.println("res = " + res);


        //单链表的反转
        System.out.println("反转链表");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        //逆向打印
        System.out.println("逆向打印");
        reversePrint(singleLinkedList.getHead());
    }

    /**
     * 从尾到头打印单链表 【百度，要求方式1：反向遍历 。 方式2：Stack栈】
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            //空链表或只有一个节点
            return;
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //遍历栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 单链表的反转【腾讯面试题，有点难度】
     */
    public static void reverseList(HeroNode head) {
        //链表为空或只有一个节点，无须反转
        if (head == null || head.next == null) {
            return;
        }

        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");

        /**
         * 思路:
         * 1. 先定义一个节点 reverseHead = new HeroNode();
         * 2. 从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端.
         * 3. 原来的链表的head.next = reverseHead.next
         */
        //TODO review
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }

        //将head.next指向reverseHead.next,实现链表的反转
        head.next = reverseHead.next;

    }

    /**
     * 查找单链表中的倒数第k个结点 【新浪面试题】
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLength(head);

        if (index <= 0 || index > size) {
            return null;
        }

        HeroNode curr = head.next;
        for (int i = 0; i < size - index; i++) {
            curr = curr.next;
        }

        return curr;
    }

    /**
     * 求单链表中有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode curr = head.next;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        return length;
    }
}

/**
 * 链表是以节点的方式来存储,是链式存储
 * 每个节点包含 data 域， next 域：指向下一个节点.
 * 如图：发现链表的各个节点不一定是连续存储.
 * 链表分带头节点的链表和没有头节点的链表，根据实际的需求来确定
 */
class SingleLinkedList {

    /**
     * 初始化一个头节点
     */
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到链表,
     * 不考虑顺序，默认添加到尾部
     */
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        //遍历链表
        while (true) {
            if (temp.next == null) {
                //找到尾部
                break;
            }
            //如果没有到最后，后移
            temp = temp.next;
        }
        //退出while，temp就指向了链表的尾部
        temp.next = heroNode;
    }

    /**
     * 根据排名将英雄插入到指定位置  (如果有这个排名，则添加失败，并给出提示)
     */
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        //遍历链表
        while (true) {
            if (temp.next == null) {
                //找到尾部
                break;
            }
            if (temp.next.no > heroNode.no) {
                //找到相应位置，就在其后插入
                break;
            } else if (temp.next.no == heroNode.no) {
                //待插入编号已存在
                flag = true;
                break;
            }
            //后移，遍历链表
            temp = temp.next;
        }

        if (flag) {
            //编号已存在
            System.out.printf("准备插入的英雄编号%d已经存在，不能加入", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 根据节点NO来修改名字或妮称
     */
    public void updateByNo(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //根据NO找到需要修改的节点
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                //已经遍历结束
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag是否需要修改节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号%d的节点，不能修改\n", newHeroNode.no);
        }
    }

    public void delNodeByNo(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到编号%d的节点，不能删除\n", no);
        }

    }

    /**
     * 遍历链表
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println("HeroNode = " + temp);
            temp = temp.next;
        }
    }

}


/**
 * 定义HereNode
 */
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}