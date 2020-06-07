package com.ls.leetcode.done;

import java.util.Stack;

/**
 * 224
 *
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 * 示例 1:
 *
 * 输入: "1 + 1"
 * 输出: 2
 * 示例 2:
 *
 * 输入: " 2-1 + 2 "
 * 输出: 3
 * 示例 3:
 *
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 */
public class Q224_CalculateI {

    public int calculate(String s) {
        Stack<Character> oper = new Stack<>();
        Stack<Integer> num = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c){
                case ' ':
                    break;
                case '(':
                    oper.push(c);
                    break;
                case ')':
                    while(!oper.peek().equals('(')){
                        calculate(oper, num);
                    }
                    oper.pop();
                    calculate(oper, num);
                    break;
                case '+':
                    oper.push(c);
                    break;
                case '-':
                    oper.push(c);
                    break;
                default:
                    StringBuilder numStr = new StringBuilder();
                    numStr.append(c);
                    while(i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9'){
                        i++;
                        numStr.append(s.charAt(i));
                    }
                    num.push(Integer.parseInt(numStr.toString()));
                    calculate(oper, num);
                    break;
            }
        }
        while(num.size() > 1){
            calculate(oper, num);
        }
        return num.pop();
    }

    public static void calculate(Stack<Character> oper, Stack<Integer> num){
        if(oper.size() > 0 && num.size() > 1){
            int ci = num.pop();
            if(oper.peek().equals('-')){
                ci = num.pop() - ci;
                oper.pop();
            } else if(oper.peek().equals('+')){
                ci = num.pop() + ci;
                oper.pop();
            }
            num.push(ci);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Q224_CalculateI().calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(new Q224_CalculateI().calculate(" 2-1 + 2 "));
        System.out.println(new Q224_CalculateI().calculate("1 + 1"));
        System.out.println(new Q224_CalculateI().calculate("1 + 11"));
        System.out.println(new Q224_CalculateI().calculate("(7)-(2)+(4)"));
        System.out.println(new Q224_CalculateI().calculate("7-2+4"));
    }

}
