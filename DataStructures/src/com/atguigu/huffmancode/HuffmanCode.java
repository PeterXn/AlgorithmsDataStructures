package com.atguigu.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author Peter
 * @date 2022/8/28 11:42
 * @description Usage
 */

public class HuffmanCode {

    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        System.out.println("content = " + content);
        byte[] bytes = content.getBytes();
        System.out.println("bytes = " + bytes.length);

        byte[] huffmanZip = huffmanZip(bytes);
        System.out.println("huffmanZip = " + Arrays.toString(huffmanZip));
        System.out.println("huffmanZip.length = " + huffmanZip.length);

        /**
         * 解码
         */

        byte[] sourceBytes = decode(huffmanCodes, huffmanZip);
        System.out.println("sourceBytes = " + new String(sourceBytes));


        /**
         * 压缩文件
         */
        String srcFile = "d://test-1.bmp";
        String dstFile = "d://test-1.zip";
        zipFile(srcFile, dstFile);
        System.out.println("文件压缩成功");

        /**
         * 解压文件
         * TODO 解压后的文件不能打开
         */
        String zipFile = "d://test-1.zip";
        String dstFiles = "d://test-2.bmp";
        unZipFile(zipFile, dstFiles);
        System.out.println("解压完成");


        /*

        List<Node> nodes = getNodes(bytes);
        System.out.println("nodes = " + nodes);

        System.out.println("霍夫曼树--前序遍历");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        huffmanTreeRoot.preOrder();

        //测试生成了对应的霍夫曼编码
        getCodes(huffmanTreeRoot);
        System.out.println("huffmanCodes = " + huffmanCodes);

        byte[] zip = zip(bytes, huffmanCodes);
        //zip = [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
        System.out.println("zip = " + Arrays.toString(zip));

         */
    }

    /**
     * 文件解压
     *
     * @param zipFile 待解压的文件
     * @param dstFile 解压后的文件存储位置
     */
    public static void unZipFile(String zipFile, String dstFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);

            byte[] huffmanBytes = (byte[]) ois.readObject();

            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] decodes = decode(huffmanCodes, huffmanBytes);
            //将decodes写入文件
            os = new FileOutputStream(dstFile);
            os.write(decodes);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //与打开顺序相反
                if (os != null) {
                    os.close();
                }
                if (ois != null) {
                    ois.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 将一个文件进行压缩
     *
     * @param srcFile 待压缩的文件路径
     * @param dstFile 压缩后的文件存储位置
     */
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream fis = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;

        try {
            fis = new FileInputStream(srcFile);
            //创建一个与原文件大小一样的byte[]
            byte[] bytes = new byte[fis.available()];
            //读取文件
            fis.read(bytes);

            //获取文件对应的霍夫曼编码表
            byte[] huffmanZipBytes = huffmanZip(bytes);

            os = new FileOutputStream(dstFile);
            //创建一个与文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);

            //以对象流的方式写入霍夫曼编码
            oos.writeObject(huffmanZipBytes);
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (os != null) {
                    os.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param huffmanCodes 霍夫曼编码表map
     * @param huffmanBytes 霍夫曼编码得到的字节数组
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {

        //1.先得到huffmanBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判读是否最后1个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //System.out.println("stringBuilder = " + stringBuilder.toString());

        //把字符串按照指定的霍夫曼编码进行解码
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //System.out.println("map = " + map);


        //创建集合，存放byte
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                /**
                 * TODO 解压时StringIndexOutOfBoundsException: String index out of range: 64587
                 * Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: 64587
                 * 	at java.lang.AbstractStringBuilder.substring(AbstractStringBuilder.java:933)
                 * 	at java.lang.StringBuilder.substring(StringBuilder.java:76)
                 * 	at com.atguigu.huffmancode.HuffmanCode.decode(HuffmanCode.java:212)
                 * 	at com.atguigu.huffmancode.HuffmanCode.unZipFile(HuffmanCode.java:92)
                 * 	at com.atguigu.huffmancode.HuffmanCode.main(HuffmanCode.java:48)
                 */
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            //i移动到count位置
            i += count;
        }

        //把list中的数据放到byte[]中
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }

        return bytes;
    }


    /**
     * 将一个byte转换成一个二进制的字符串
     *
     * @param flag 标志是否需要补高位；如true则需要补高位
     * @param b    传入的byte
     * @return b对应的二进制字符串(补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;

        //如果是正数，须补高位
        //按位与
        if (flag) {
            temp |= 256;
        }

        String str = Integer.toBinaryString(temp);

        //System.out.println("str = " + str);

        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * @param bytes 原始的字符串对应的字节数组
     * @return 霍夫曼编码处理后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes) {

        List<Node> nodes = getNodes(bytes);

        //根据nodes创建的霍夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);

        //根据霍夫曼树生成对应的霍夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);

        //根据霍夫曼编码压缩得到压缩后的霍夫曼编码字节的数组
        byte[] huffmanCodeByte = zip(bytes, huffmanCodes);

        return huffmanCodeByte;
    }

    /**
     * @param bytes        这是原始的字符串对应的byte[]
     * @param huffmanCodes 生成的霍夫曼编码map
     * @return 返回霍夫曼编码处理后的bytes[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用huffmanCode将bytes转成huffman对应的字符串
        StringBuilder sb = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            sb.append(huffmanCodes.get(b));
        }

        //System.out.println("sb = " + sb);

        int len = 0;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }

        //创建存储奉压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        //因为每8位对应一个byte，步长是8
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;
            if (i + 8 > sb.length()) {
                //不够8位
                strByte = sb.substring(i);
            } else {
                strByte = sb.substring(i, i + 8);
            }
            //将strByte转成一个byte，放入huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;

        }

        return huffmanCodeBytes;
    }

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理左子树
        getCodes(root.getLeft(), "0", stringBuilder);
        //处理右子树
        getCodes(root.getRight(), "1", stringBuilder);

        return huffmanCodes;
    }


    /**
     * 生成霍夫曼树对应的霍夫曼编码
     * 功能：将传入的 node结点的所有叶子结点的赫夫曼编码得到，并放入到 huffmanCodes集合
     *
     * @param node          传入结点
     * @param code          路径，左子结点是0，右子结点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder sb = new StringBuilder(stringBuilder);
        //将code添加至sb中
        sb.append(code);
        if (node != null) {
            //判断当前Node是叶子结点还是非叶子结点
            if (node.getData() == null) {
                //非叶子结点
                //递归如理，向左
                getCodes(node.getLeft(), "0", sb);
                //递归如理，向右
                getCodes(node.getRight(), "1", sb);
            } else {
                //说明是叶子结点
                huffmanCodes.put(node.getData(), sb.toString());
            }
        }
    }


    /**
     * 前序遍历
     */
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("霍夫曼树为空，无法遍历");
        }
    }

    /**
     * @param bytes 接收字节数组
     * @return
     */
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();

        //遍历bytes，统计每一个byte出现的次数
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //把每一个键值对转换一个node对象，并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    /**
     * 通过list创建对应的霍夫曼树
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序，从小到大
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树,它的根节点 没有 data,只有权值
            Node parent = new Node(null, leftNode.getWeight() + rightNode.getWeight());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }

        //返回根结点
        return nodes.get(0);
    }
}

/**
 * 创建Node
 */
class Node implements Comparable<Node> {
    private Byte data;
    private int weight;
    private Node left;
    private Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
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
