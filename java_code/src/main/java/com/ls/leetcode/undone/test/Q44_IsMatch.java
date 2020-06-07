package com.ls.leetcode.undone.test;

/**
 * 44
 *
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 *
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 *
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 *
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输入: false
 *
 */
public class Q44_IsMatch {

    public boolean isMatch(String s, String p) {
        if(s == null || p == null){
            return false;
        }
        return isMatch(s, p, 0, 0);
    }

    private boolean isMatch(String s, String p, int si, int pi) {
        if(pi == p.length()){
            return si == s.length();
        }

        if(p.charAt(pi) != '*'){
            if(si < s.length() && (p.charAt(pi) == '?' || s.charAt(si) == p.charAt(pi))){
                return isMatch(s, p, si + 1, pi + 1);
            } else {
                return false;
            }
        }

        while (pi < p.length() && p.charAt(pi) == '*'){
            pi++;
        }

        if(pi == p.length()){
            return true;
        }

        for (int i = si; i < s.length(); i++) {
            if(s.charAt(i) == p.charAt(pi) && isMatch(s, p, i + 1, pi + 1)){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
//        System.out.println(new Q44_IsMatch().isMatch("aa", "a"));
//        System.out.println(new Q44_IsMatch().isMatch("aa", "*"));
//        System.out.println(new Q44_IsMatch().isMatch("cb", "?a"));
//        System.out.println(new Q44_IsMatch().isMatch("adceb", "*a*b"));
//        System.out.println(new Q44_IsMatch().isMatch("adceb", "*a*b*"));
//        System.out.println(new Q44_IsMatch().isMatch("acdcb", "a*c?b"));
//        System.out.println(new Q44_IsMatch().isMatch("acdcb", "a*c?b*"));
//        System.out.println(new Q44_IsMatch().isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b"));
        System.out.println(new Q44_IsMatch().isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
                "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
    }

}
