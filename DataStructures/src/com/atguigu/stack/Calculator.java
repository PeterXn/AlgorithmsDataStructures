package com.atguigu.stack;

/**
 * @author Peter
 * @date 2022/8/21 13:04
 * @description 以栈实现计算计功能
 */

public class Calculator {

    public static void main(String[] args) {
        /**
         * TODO "9-2*1-2"=有bug
         */
        String expression = "9-2*1-2";
        CalcStack numStack = new CalcStack(10);
        CalcStack operStack = new CalcStack(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        //拼接多位数据
        String keepNum = "";

        while (true) {
            //获取expression的字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断 ch是什么，然后做相应的处理

            if (operStack.isOper(ch)) {
                //如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符,就需要从数栈中 pop出两个数,
                    //在从符号栈中 pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        //如果当前的操作符的优先级小于或者等于栈中的操作符
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
                        operStack.push(ch);
                    }
                } else {
                    //如果为空直接入符号栈..
                    operStack.push(ch);
                }
            } else {
                //如果是数字，直接入数栈
                //numStack.push(ch - 48);
                //numStack.push(Integer.parseInt(String.valueOf(ch)));

                //处理多位数
                keepNum += ch;

                //如果 ch已经是 expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                } else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //注意是看后一位，不是 index++
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }

            }

            //遍历表达式
            index++;
            if (index >= expression.length()) {
                //退出while
                break;
            }
        }

        //表达式处理完毕
        while (true) {
            //如果符号栈为空，则计算到最后的结果,数栈中只有一个数字【结果】
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }

        System.out.printf("表达式: %s = %d", expression, numStack.peek());

    }
}


/**
 * 计算器栈
 */
class CalcStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public CalcStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈，从栈顶开始显示
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        //从栈顶开始显示
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 返回运算符的优先级，数字越大优先级越高
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是否是运算符
     */
    public boolean isOper(char val) {
        return val == '*' || val == '/' || val == '+' || val == '-';
    }

    /**
     * 计算
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                //注意顺序
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                //注意顺序
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    public int peek() {
        return stack[top];
    }

}