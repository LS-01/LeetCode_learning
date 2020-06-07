package com.ls.leetcode.done;

import java.util.HashMap;
import java.util.Map;

/**
 * 70
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 斐波那契数列
 *
 * 1
 * 执行用时 : 1 ms, 在所有 Java 提交中击败了8.83%的用户
 * 内存消耗 : 36.5 MB, 在所有 Java 提交中击败了5.07%的用户
 *
 * 2
 * 执行用时 : 0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗 : 36.3 MB, 在所有 Java 提交中击败了5.07%的用户
 *
 */
public class Q70_ClimbStairs {

    public int climbStairs1(int n) {
        return climb(n, new HashMap<Integer, Integer>());
    }

    private int climb(int n, Map<Integer, Integer> map){
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        if(map.containsKey(n)){
            return map.get(n);
        } else {
            int i = climb(n - 1, map) + climb(n - 2, map);
            map.put(n, i);
            return i;
        }
    }

    public int climbStairs2(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new Q70_ClimbStairs().climbStairs1(2));
        System.out.println(new Q70_ClimbStairs().climbStairs1(3));
        System.out.println(new Q70_ClimbStairs().climbStairs1(40));

        System.out.println(new Q70_ClimbStairs().climbStairs2(2));
        System.out.println(new Q70_ClimbStairs().climbStairs2(3));
        System.out.println(new Q70_ClimbStairs().climbStairs2(40));
    }
}
