package com.atguigu.tree;

/**
 * @author Peter
 * @date 2022/8/25 9:38
 * @description 二叉树遍历
 */

public class BinaryTreeDemo {

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        System.out.println("前序遍历");
        /**
         * HeroNode{no=1, name='宋江'}
         * HeroNode{no=2, name='吴用'}
         * HeroNode{no=3, name='卢俊义'}
         * HeroNode{no=4, name='林冲'}
         */
        binaryTree.preOrder();

        System.out.println("中序遍历");
        /**
         * HeroNode{no=2, name='吴用'}
         * HeroNode{no=1, name='宋江'}
         * HeroNode{no=3, name='卢俊义'}
         * HeroNode{no=4, name='林冲'}
         */
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        //2->4->3->1
        binaryTree.postOrder();

        System.out.println("前序查找");
        HeroNode preNode = binaryTree.preOrderSearch(5);
        System.out.println("preNode = " + preNode);


        System.out.println("中序查找");
        HeroNode infixNode = binaryTree.infixOrderSearch(5);
        System.out.println("infixNode = " + infixNode);

        System.out.println("后序查找");
        HeroNode postNode = binaryTree.postOrderSearch(5);
        System.out.println("postNode = " + postNode);

        System.out.println("删除前");
        binaryTree.preOrder();
        binaryTree.delNode(0);
        System.out.println("删除后");
        binaryTree.preOrder();
    }

}

/**
 * 二叉树
 */
class BinaryTree {

    private HeroNode root;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void delNode(int no) {
        if (root != null) {
            //root是不是被删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }

        } else {
            System.out.println("空树，不能删除");
        }
    }
    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.proOrder();
        } else {
            System.out.println("二叉为空，无法遍历");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉为空，无法遍历");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉为空，无法遍历");
        }
    }

    /**
     * 前序查找
     */
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            System.out.println("当前为 " + no + " 的HeroNode不存在");
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            System.out.println("当前为 " + no + " 的HeroNode不存在");
            return null;
        }
    }

    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            System.out.println("当前为 " + no + " 的HeroNode不存在");
            return null;
        }
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public void delNode(int no) {
        /**
         *
         * 1.因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断
         * 当前这个结点是不是需要删除结点
         * 2.如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将 this.left = null;并且就返回
         * (结束递归删除)
         * 3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将 this.right= null ;并且就返回
         * (结束递归删除)
         * 4.如果第 2和第 3步没有删除结点，那么我们就需要向左子树进行递归删除
         * 5.如果第 4步也没有删除结点，则应当向右子树进行递归删除.
         * */
        //2.如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将 this.left = null;并且就返回(结束递归删除)
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将 this.right= null ;并且就返回(结束递归删除)
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //4.我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //5.则应当向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }


    }

    /**
     * 前序遍历
     */
    public void proOrder() {
        //先输出父结点
        System.out.println(this);

        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.proOrder();
        }

        //递归向右子树
        if (this.right != null) {
            this.right.proOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        //父节点
        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序查找~~");
        if (this.no == no) {
            return this;
        }

        HeroNode resNode = null;
        //向左递归查找
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //向右递归查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }

        return resNode;
    }

    /**
     * 中序遍历查找
     */
    public HeroNode infixOrderSearch(int no) {

        HeroNode resNode = null;
        //向左递归查找
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        System.out.println("进入中序查找~~");
        if (this.no == no) {
            return this;
        }
        //向右递归查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }

        return resNode;
    }

    /**
     * 后序查找
     */
    public HeroNode postOrderSearch(int no) {

        HeroNode resNode = null;
        //向左递归查找
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        //向右递归查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        System.out.println("进入后序查找~~");
        if (this.no == no) {
            return this;
        }

        return resNode;
    }
}
