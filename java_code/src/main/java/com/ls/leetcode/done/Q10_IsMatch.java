package com.ls.leetcode.done;

/**
 * 10
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
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
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 * 执行用时 :1952 ms, 在所有 Java 提交中击败了5.00%的用户
 * 内存消耗 :41.4 MB, 在所有 Java 提交中击败了7.36%的用户
 *
 */
public class Q10_IsMatch {

    public boolean isMatch(String s, String p) {
        if(s == null || p == null){
            return false;
        }
        return isMatch(s, p, 0, 0);
    }

    private boolean isMatch(String s, String p, int si, int pi){
        if(pi == p.length()){
            return si == s.length();
        }

        if(pi + 1 >= p.length() || p.charAt(pi + 1) != '*'){
            if(si < s.length() && (p.charAt(pi) == '.' || s.charAt(si) == p.charAt(pi))){
                return isMatch(s, p, si + 1, pi + 1);
            } else {
                return false;
            }
        }

        String left = p.substring(0, pi);
        String right = p.substring(pi + 2);
        StringBuilder middle = new StringBuilder();
        boolean b = isMatch(s, left + middle + right, si, pi);
        while (!b){
            middle.append(p.charAt(pi));
            if(middle.length() > s.length()){
                return false;
            }
            b = isMatch(s, left + middle + right, si, pi);
        }
        return true;
    }

    //todo some better solutions
    /**
     * solution1
     * from top to end
     */
    public boolean isMatch1(String s, String p) {
        if(s == null || p == null){
            return false;
        }
        return isMatch1(s, p, 0, 0);
    }

    private boolean isMatch1(String s, String p, int si, int pi){
        if(pi == p.length()){
            return si == s.length();
        }

        if(pi + 1 == p.length() || p.charAt(pi + 1) != '*'){
            return (si < s.length()) && (p.charAt(pi) == '.' || s.charAt(si) == p.charAt(pi)) && isMatch1(s, p, si + 1, pi + 1);
        }

        if(pi + 1 < p.length() && p.charAt(pi + 1) == '*'){
            while (si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.')){
                if(isMatch1(s, p, si, pi + 2)){
                    return true;
                }
                si++;
            }
        }
        return isMatch1(s, p, si, pi + 2);
    }

    /**
     * solution2
     * from end to top
     */
    public boolean isMatch2(String s, String p) {
        if(s == null || p == null){
            return false;
        }
        return isMatch2(s, p, 0, 0);
    }

    private boolean isMatch2(String s, String p, int si, int pi){
        if(pi == 0){
            return si == 0;
        }

        if(si == 0){
            return pi > 1 && p.charAt(pi - 1) == '*' && isMatch2(s, p, si, pi - 2);
        }

        if(p.charAt(pi - 1) != '*'){
            return isMatch(s.charAt(si - 1), p.charAt(pi - 1)) && isMatch2(s, p, si - 1, pi - 1);
        }

        return isMatch2(s, p, si, pi - 2) || isMatch2(s, p, si - 1, pi - 1) && isMatch(s.charAt(si - 1), p.charAt(pi - 2));
    }

    private boolean isMatch(char a, char b) {
        return a == b || b == '.';
    }

    /**
     * solution3
     * 动态规划法
     * 运用动态规划，把时间复杂度控制在 O(n^2)，而空间复杂度也是 O(n^2)。
     */
    public boolean isMatch3(String s, String p) {
        int m = s.length(), n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;

        for (int j = 1; j <= n; j++) {
            dp[0][j] = j > 1 && p.charAt(j - 1) == '*' && dp[0][j - 2];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] &&
                            isMatch(s.charAt(i - 1), p.charAt(j - 1));
                } else {
                    dp[i][j] = dp[i][j - 2] || dp[i - 1][j] &&
                            isMatch(s.charAt(i - 1), p.charAt(j - 2));
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new Q10_IsMatch().isMatch("aa", "a"));
        System.out.println(new Q10_IsMatch().isMatch("aa", "a*"));
        System.out.println(new Q10_IsMatch().isMatch("ab", ".*"));
        System.out.println(new Q10_IsMatch().isMatch("aab", "c*a*b"));
        System.out.println(new Q10_IsMatch().isMatch("mississippi", "mis*is*p*."));
    }

}