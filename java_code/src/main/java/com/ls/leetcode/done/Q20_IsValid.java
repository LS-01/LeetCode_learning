package com.ls.leetcode.done;

import java.util.Stack;

/**
 * 20
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 */
public class Q20_IsValid {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c){
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    if(stack.size() > 0 && stack.peek().equals('(')){
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                    break;
                case ']':
                    if(stack.size() > 0 && stack.peek().equals('[')){
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                    break;
                case '}':
                    if(stack.size() > 0 && stack.peek().equals('{')){
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                    break;
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        System.out.println(new Q20_IsValid().isValid("(]]]"));
    }

}
