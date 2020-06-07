package com.ls.leetcode.done;

/**
 * 91
 *
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 * 执行用时 : 1447 ms, 在所有 Java 提交中击败了5.01%的用户
 * 内存消耗 : 37.6 MB, 在所有 Java 提交中击败了5.05%的用户
 *
 */
public class Q91_NumDecodings {

    public int numDecodings(String s) {
        return decode(s, 0);
    }

    private int decode(String s, int start){

        if(start == s.length()){
            return 1;
        }

        if(start > s.length()){
            return 0;
        }

        int count = 0;
        char c = s.charAt(start);
        if(c > '0'){
            count += decode(s, start + 1);
        }

        if(start < s.length() - 1){
            char cn = s.charAt(start + 1);
            if(c == '1' || (c == '2' && cn < '7') ){
                count += decode(s, start + 2);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Q91_NumDecodings().numDecodings("1"));
        System.out.println(new Q91_NumDecodings().numDecodings("12"));
        System.out.println(new Q91_NumDecodings().numDecodings("226"));
        System.out.println(new Q91_NumDecodings().numDecodings("826"));
    }
}
