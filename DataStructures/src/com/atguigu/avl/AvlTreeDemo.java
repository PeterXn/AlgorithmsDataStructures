package com.atguigu.avl;

/**
 * @author Peter
 * @date 2022/8/30 21:52
 * @description 平衡二叉树
 */

public class AvlTreeDemo {
    public static void main(String[] args) {
        int[] leftArr = {4, 3, 6, 5, 7, 8};
        int[] rightArr = {10, 12, 8, 9, 7, 6};
        int[] doubleArr = {10, 11, 7, 6, 8, 9};

        AvlTree avlTree = new AvlTree();
        //添加结点
        for (int i = 0; i < doubleArr.length; i++) {
            avlTree.add(new Node(doubleArr[i]));
        }

        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("**********平衡处理之后**********************");
        System.out.println("avlTree树高度：" + avlTree.getRoot().height());
        System.out.println("avlTree左子树高度：" + avlTree.getRoot().leftHeight());
        System.out.println("avlTree右子树高度：" + avlTree.getRoot().rightHeight());
        System.out.println("avlTree.getRoot() = " + avlTree.getRoot().getLeft().getLeft());

    }
}

class AvlTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 删除结点
     */
    public void deleteNode(int value) {
        if (root == null) {
            System.out.println("AvlTree.deleteNode");
            return;
        } else {
            //1.查找待删除的结点
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果发现当前这棵树只有一个结点
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
                return;
            }

            //去找targetNode的父结点
            Node parent = searchParent(value);
            if (targetNode.getLeft() == null && targetNode.getRight() == null) {
                //1.删除的结点是叶子结点
                //targetNode是parent的左子结点，还是右子结点？
                if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                    //targetNode是parent的左子结点
                    parent.setLeft(null);
                } else if (parent.getRight() != null && parent.getRight().getValue() == value) {
                    //targetNode是parent的右子结点
                    parent.setRight(null);
                }
            } else if (targetNode.getLeft() != null && targetNode.getRight() != null) {
                //2.删除2个子树的结点
                int min = deleteRightTreeMin(targetNode.getRight());
                targetNode.setValue(min);
            } else {
                //3.删除只有1个子树的结点
                if (targetNode.getLeft() != null) {
                    if (parent != null) {
                        //targetNode有左子结点
                        if (parent.getLeft().getValue() == value) {
                            //targetNode是parent的左子结点
                            parent.setLeft(targetNode.getLeft());
                        } else {
                            //targetNode是parent的右子结点
                            parent.setRight(targetNode.getLeft());
                        }
                    } else {
                        root = targetNode.getLeft();
                    }
                } else {
                    if (parent != null) {
                        //targetNode有右子结点 targetNode.getRight() != null
                        if (parent.getLeft().getValue() == value) {
                            //targetNode是parent的左子结点
                            parent.setLeft(targetNode.getRight());
                        } else {
                            //targetNode是parent的右子结点
                            parent.setRight(targetNode.getRight());
                        }
                    } else {
                        root = targetNode.getRight();
                    }
                }
            }
        }
    }

    /**
     * @param node 传入的结点（当做树的root结点）
     * @return 以node为根结点的排序二叉树中的最小的值
     */
    public int deleteRightTreeMin(Node node) {
        Node target = node;
        while (target.getLeft() != null) {
            target = target.getLeft();
        }
        // target指向了最小结点
        //删除最小结点并返回这个结点的值
        deleteNode(target.getValue());
        return target.getValue();
    }


    /**
     * @param node 传入的结点（当做树的root结点）
     * @return 以node为根结点的排序二叉树中的最大的值
     */
    public int deleteLeftTreeMax(Node node) {
        Node target = node;
        while (target.getRight() != null) {
            target = target.getRight();
        }
        // target指向了最大结点
        //删除最大结点并返回这个结点的值
        deleteNode(target.getValue());
        return target.getValue();
    }


    /**
     * 查找要删除的结点
     *
     * @param value 删除结点的值
     * @return
     */
    public Node search(int value) {
        if (root != null) {
            return root.search(value);
        } else {
            System.out.println("AvlTree.search");
            return null;
        }
    }

    /**
     * 查找父结点
     */
    public Node searchParent(int value) {
        if (root == null) {
            System.out.println("AvlTree.searchParent");
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 添加
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("AvlTree.infixOrder----空树，无法遍历");
        }
    }
}


class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 左旋转
     */
    private void leftRotate() {
        //保存当前结点的值
        Node newNode = new Node(value);
        //新的结点的左子树设置当前结点的左子树
        newNode.left = left;
        //新的结点的右子树设置成当前结点的右子树的左子树
        newNode.right = right.left;
        //当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
        //把当前结点的左子结点设置成新的结点
        left = newNode;
    }

    /**
     * 右旋转
     */
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    /**
     * 左子树的高度
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.height();
        }
    }

    /**
     * 右子树的高度
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        } else {
            return right.height();
        }
    }

    /**
     * 返回当前结点为根结点的树的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 查找删除结点的value
     */
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {
            //左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找删除结点的父结点
     *
     * @param value 删除结点的值
     * @return 删除结点的父结点，或者Null
     */
    public Node searchParent(int value) {
        if (this.left != null && this.left.value == value) {
            return this;
        }
        if (this.right != null && this.right.value == value) {
            return this;
        }

        if (value < this.value && this.left != null) {
            //如果查找的值小于当前结点的值，且当前结点的左子结点不为空，向左查找
            return this.left.searchParent(value);
        } else if (value >= this.value && this.right != null) {
            //如果查找的值大于当前结点的值，且当前结点的右子结点不为空，向右查找
            return this.right.searchParent(value);
        } else {
            //没有找到父结点
            return null;
        }
    }

    /**
     * 添加结节
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }

        //传入的结点值与当前子树根结点的大小
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        /*
         * 当添加一个结点，如果右子树的高度-左子树的高度>1，左旋转
         */
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树高度大于它的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //右子结点右旋转
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }

            //!!!
            return;
        }

        /*
         * 当添加一个结点，如果左子树的高度-右子树的高度>1，右旋转
         */
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前结点的左结点进行左旋转
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
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
}
