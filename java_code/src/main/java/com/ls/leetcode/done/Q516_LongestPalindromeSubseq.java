package com.ls.leetcode.done;

/**
 * 516
 *
 * 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
 *
 * 示例 1:
 * 输入:
 *
 * "bbbab"
 * 输出:
 *
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 *
 * 示例 2:
 * 输入:
 *
 * "cbbd"
 * 输出:
 *
 * 2
 * 一个可能的最长回文子序列为 "bb"。
 *
 * 执行用时 : 50 ms, 在所有 Java 提交中击败了19.62%的用户
 * 内存消耗 : 53.5 MB, 在所有 Java 提交中击败了5.11%的用户
 *
 */
public class Q516_LongestPalindromeSubseq {

    //todo need more practice
    public int longestPalindromeSubseq(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= length; len++) {
            for (int i = 0; i < length - len + 1; i++) {
                int j = i + len - 1;
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = 2 + (len == 2 ? 0 : dp[i + 1][j - 1]);
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][length - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Q516_LongestPalindromeSubseq().longestPalindromeSubseq("bbbab"));
        System.out.println(new Q516_LongestPalindromeSubseq().longestPalindromeSubseq("cbbd"));
        System.out.println(new Q516_LongestPalindromeSubseq().longestPalindromeSubseq("bababab"));
    }
}
