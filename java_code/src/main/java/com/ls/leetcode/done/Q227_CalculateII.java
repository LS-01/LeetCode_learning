package com.ls.leetcode.done;

import java.util.Stack;

/**
 * 227
 *
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * 示例 1:
 *
 * 输入: "3+2*2"
 * 输出: 7
 * 示例 2:
 *
 * 输入: " 3/2 "
 * 输出: 1
 * 示例 3:
 *
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 */
public class Q227_CalculateII {

    public int calculate(String s) {
        Stack<Character> oper = new Stack<>();
        Stack<Integer> num = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c){
                case ' ':
                    break;
                case '+':
                case '-':
                    calculateSum(oper, num);
                    oper.push(c);
                    break;
                case '*':
                case '/':
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
                    calculateTime(oper, num);
                    break;
            }
        }
        while(num.size() > 1){
            calculateSum(oper, num);
        }
        return num.pop();
    }

    public static void calculateSum(Stack<Character> oper, Stack<Integer> num){
        if(oper.size() > 0){
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

    public static void calculateTime(Stack<Character> oper, Stack<Integer> num){
        if(oper.size() > 0){
            int ci = num.pop();
            if(oper.peek().equals('*')){
                ci = num.pop() * ci;
                oper.pop();
            } else if(oper.peek().equals('/')){
                ci = num.pop() / ci;
                oper.pop();
            }
            num.push(ci);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Q227_CalculateII().calculate("3+2*2"));
        System.out.println(new Q227_CalculateII().calculate(" 3/2 "));
        System.out.println(new Q227_CalculateII().calculate(" 3+5 / 2 "));
        System.out.println(new Q227_CalculateII().calculate(" 3+50 / 2 "));
    }

}
