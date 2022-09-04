package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * @author Peter
 * @date 2022/8/20 0:37
 * @description 单向链表
 */

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        System.out.println("双向链表测试");
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");

        System.out.println("有序插入**********************************************");
        DoubleLinkedList doubleLinkedList2 = new DoubleLinkedList();
        doubleLinkedList2.addByOrder(heroNode1);
        doubleLinkedList2.addByOrder(heroNode4);
        doubleLinkedList2.addByOrder(heroNode3);
        doubleLinkedList2.addByOrder(heroNode2);
        doubleLinkedList2.list();

        System.out.println("链表尾部插入**********************************************");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);

        doubleLinkedList.list();


        System.out.println("修改双向链表测试");
        heroNode4 = new HeroNode2(4, "林冲123", "豹子头1234");
        doubleLinkedList.updateByNo(heroNode4);
        doubleLinkedList.list();

        System.out.println("删除双向链表测试");
        doubleLinkedList.delNodeByNo(3);
        doubleLinkedList.list();
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
class DoubleLinkedList {

    /**
     * 初始化一个头节点
     */
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 添加节点到链表,
     * 不考虑顺序，默认添加到尾部
     */
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
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
        heroNode.pre = temp;
    }

    /**
     * 根据排名将英雄插入到指定位置  (如果有这个排名，则添加失败，并给出提示)
     */
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        //头节点的情况此时链表没有数据所以直接插入就好
        if (temp.next == null) {
            temp.next = heroNode;
            heroNode.pre = temp;
            //直接结束掉此方法
            return;
        }

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
            System.out.printf("准备插入的英雄编号%d已经存在，不能加入\n", heroNode.no);
        } else {
            if (temp.next == null) {
                //链表尾插入
                temp.next = heroNode;
                heroNode.pre = temp;
            } else {
                heroNode.pre = temp;
                heroNode.next = temp.next;
                temp.next.pre = heroNode;
                temp.next = heroNode;
            }

        }
    }

    /**
     * 根据节点NO来修改名字或妮称
     */
    public void updateByNo(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //根据NO找到需要修改的节点
        HeroNode2 temp = head.next;
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
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //可以删除
            temp.pre.next = temp.next;
            //删除最后一个节点？
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
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
        HeroNode2 temp = head.next;
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
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
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