package com.atguigu.hashtab;

import java.util.Scanner;

/**
 * @author Peter
 * @date 2022/8/25 0:12
 * @description Usage
 */

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add : 添加雇员");
            System.out.println("list: 遍历雇员");
            System.out.println("find: 查找雇员");
            System.out.println("del : 删除雇员");
            System.out.println("exit: 退出系统");
            System.out.print("请输入：");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.print("输入id：");
                    int id = scanner.nextInt();
                    System.out.print("输入名字：");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.print("输入id：");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "del":
                    hashTab.del(1);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}

/**
 * 创建HashTab
 */
class HashTab {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        //？留一个坑,这时不要分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加会员
     */
    public void add(Emp emp) {
        int empLinkedListNo = hashFun(emp.id);
        empLinkedLists[empLinkedListNo].add(emp);
    }

    /**
     * 遍历哈希表
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    public void del(int id) {

    }

    /**
     * 查找
     */
    public void findEmpById(int id) {
        int hash = hashFun(id);
        Emp empById = empLinkedLists[hash].findEmpById(id);
        if (empById != null) {
            System.out.printf("在第%d条链表中找到雇员 id=%d\n", hash + 1, id);
        } else {
            System.out.println("没有找到这个"+id+"的雇员");
        }
    }

    /**
     * 散列函数
     */
    public int hashFun(int id) {
        return id % size;
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

/**
 * EmpLinkedList
 */
class EmpLinkedList {
    //头指针，执行第一个 Emp,因此我们这个链表的 head是直接指向第一个 Emp
    private Emp head;

    /**
     * 默认添加到链表最后
     *
     * @param emp
     */
    public void add(Emp emp) {
        //添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //不是添加第一个雇员
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        //添加emp
        curEmp.next = emp;
    }

    /**
     * 遍历链表
     */
    public void list(int i) {
        if (head == null) {
            System.out.println("第 " + i + " 链表为空");
            return;
        }
        System.out.print("第 " + i + " 链表信息为：");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    /**
     * 根据id查找
     */
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }


}