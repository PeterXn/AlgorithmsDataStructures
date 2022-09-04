package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Peter
 * @date 2022/8/21 16:09
 * @description 后缀表达式又称逆波兰表达式
 * <p>
 * 后缀表达式的计算机求值
 * <p>
 * 从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算（次顶元素 和 栈顶元素），
 * 并将结果入栈；重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果
 * <p>
 * 例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
 * <p>
 * 从左至右扫描，将3和4压入堆栈；
 * 遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
 * 将5入栈；
 * 接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
 * 将6入栈；
 * 最后是-运算符，计算出35-6的值，即29，由此得出最终结果
 */

public class PolandNotation {
    public static void main(String[] args) {
        String str = "3 4 + 5 * 6 -";
        List<String> string = getListString(str);
        System.out.println("string = " + string);
        int calculate = calculate(string);
        System.out.println("calculate = " + calculate);

        /**
         * 将中缀表达式“1+((2+3)×4)-5”转 换为后缀表达式的过程如下
         *
         * 因此结果为
         * "1 2 3 + 4 × + 5 –"
         */
        String infixStr = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(infixStr);
        System.out.println("list = " + list);
        List<String> parseSuffixExpressList = parseSuffixExpressList(list);
        // [1, 2, 3, +, 4, *, +, 5, -]
        System.out.println("parseSuffixExpressList = " + parseSuffixExpressList);
        System.out.printf("后缘表达式计算：%s = %d", parseSuffixExpressList, calculate(parseSuffixExpressList));
    }

    /**
     * 具体步骤如下:
     * 1.初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     * 2.从左至右扫描中缀表达式；
     * 3.遇到操作数时，将其压s2；
     * 4.遇到运算符时，比较其与s1栈顶运算符的优先级：
     * 4.1 如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     * 4.2 否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     * 4.3 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
     * 5.遇到括号时：
     * (1) 如果是左括号“(”，则直接压入s1
     * (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6.重复步骤2至5，直到表达式的最右边
     * 7.将s1中剩余的运算符依次弹出并压入s2
     * 8.依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     */
    public static List<String> parseSuffixExpressList(List<String> list) {
        Stack<String> stack = new Stack<>();
        ArrayList<String> arrayList = new ArrayList<>();

        for (String item : list) {
            //如果是一个数
            if (item.matches("\\d+")) {
                arrayList.add(item);
            } else if ("(".equals(item)) {
                stack.push(item);
            } else if (")".equals(item)) {
                while (!"(".equals(stack.peek())) {
                    arrayList.add(stack.pop());
                }
                stack.pop();
            } else {
                while (stack.size() != 0 && Operation.getValue(stack.peek()) >= Operation.getValue(item)) {
                    arrayList.add(stack.pop());
                }
                stack.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并压入s2
        while (stack.size() != 0) {
            arrayList.add(stack.pop());
        }

        return arrayList;
    }

    /**
     * 将中缀表达式“1+((2+3)×4)-5”转换为对应的list:
     * 1, +, (, (, 2, +, 3, ), ×, 4, ), -, 5
     */
    public static List<String> toInfixExpressionList(String s) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        String str = "";
        char c = ' ';
        do {
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add(String.valueOf(c));
                i++;
            } else {
                //如果是数字，考虑多位数
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());

        return list;
    }

    public static List<String> getListString(String str) {
        String[] split = str.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }

        return list;
    }

    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            //正则表达式
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                //操作符
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(String.valueOf(res));
            }
        }

        return Integer.parseInt(stack.pop());
    }
}

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String key) {
        int result = 0;
        switch (key) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("运算符错误");
                break;
        }
        return result;
    }
}
