package com.ls.leetcode.done;

/**
 * 300
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * 自底向上指，通过状态转移方程，从最小的问题规模入手，不断地增加问题规模，直到所要求的问题规模为止。依然使用记忆化避免重复的计算，不需要递归。
 *
 * 建议：在面试的时候，如果能最终给出一个自底向上的方案和代码，则比较完美。
 *
 * 执行用时 : 11 ms, 在所有 Java 提交中击败了71.60%的用户
 * 内存消耗 : 37.6 MB, 在所有 Java 提交中击败了5.11%的用户
 */
public class Q300_LengthOfLIS {

    //todo 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
    public int lengthOfLIS(int[] nums) {
        int max = 0;
        int length = nums.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if(nums[i] > nums[j] && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                }
            }
            if(max < dp[i]){
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Q300_LengthOfLIS().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
}
