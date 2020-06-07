package com.ls.leetcode.done;

import java.util.HashMap;
import java.util.Map;

/**
 * 198
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 *
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 1
 * 执行用时 : 1 ms, 在所有 Java 提交中击败了7.68%的用户
 * 内存消耗 : 37.3 MB, 在所有 Java 提交中击败了5.05%的用户
 *
 * 2
 * 执行用时 : 0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗 : 37 MB, 在所有 Java 提交中击败了5.05%的用户
 *
 */
public class Q198_Rob {

    public int rob1(int[] nums) {
        return r(nums, 0, new HashMap<Integer, Integer>());
    }

    private int r(int[] nums, int i, Map<Integer, Integer> map){
        if(i >= nums.length){
            return 0;
        }
        if(map.containsKey(i)){
            return map.get(i);
        } else {
            int i1 = r(nums, i + 2, map) + nums[i];
            int i2 = r(nums, i + 1, map);
            int max = i1 >= i2 ? i1 : i2;
            map.put(i, max);
            return max;
        }
    }

    public int rob2(int[] nums) {
        int length = nums.length;
        if(length == 0){
            return 0;
        }
        if(length == 1){
            return nums[0];
        }

        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[length - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Q198_Rob().rob1(new int[]{}));
        System.out.println(new Q198_Rob().rob1(new int[]{1}));
        System.out.println(new Q198_Rob().rob1(new int[]{1,2,3,1}));
        System.out.println(new Q198_Rob().rob1(new int[]{2,7,9,3,1}));
        System.out.println(new Q198_Rob().rob1(new int[]{2,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1}));

        System.out.println(new Q198_Rob().rob2(new int[]{}));
        System.out.println(new Q198_Rob().rob2(new int[]{1}));
        System.out.println(new Q198_Rob().rob2(new int[]{1,2,3,1}));
        System.out.println(new Q198_Rob().rob2(new int[]{2,7,9,3,1}));
        System.out.println(new Q198_Rob().rob2(new int[]{2,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1,7,9,3,1}));
    }
}
