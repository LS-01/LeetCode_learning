package com.ls.leetcode.done;

import java.util.Stack;

/**
 * 84
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 * 最大矩形面积为 10 个单位。
 *
 *
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 *
 * 执行用时 :1067 ms , 在所有 Java 提交中击败了5.61%的用户
 * 内存消耗 :40.8 MB , 在所有 Java 提交中击败了77.20%的用户
 *
 * 执行用时 :12 ms, 在所有 Java 提交中击败了87.61%的用户
 * 内存消耗 :41.4 MB, 在所有 Java 提交中击败了44.44%的用户
 */
public class Q84_LargestRectangleArea {

    public int largestRectangleArea1(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = heights[i];
            for (int j = i; j < heights.length; j++) {
                if(heights[j] < minHeight){
                    minHeight = heights[j];
                }
                int v = minHeight * (j - i + 1);
                if(v > max){
                    max = v;
                }
            }
        }
        return max;
    }

    public int largestRectangleArea2(int[] heights) {
        int max = 0;
        int length = heights.length;
        Stack<Integer> stack = new Stack<>();

        int i = 0;
        while (i <= length){
            while (!stack.empty() && (i == length || heights[i] < heights[stack.peek()])){
                int h = heights[stack.pop()];
                int w = stack.empty() ? i : i - 1 - stack.peek();
                max = Math.max(max, h * w);
            }
            stack.push(i++);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Q84_LargestRectangleArea().largestRectangleArea1(new int[]{2,1,5,6,2,3}));
        System.out.println(new Q84_LargestRectangleArea().largestRectangleArea1(new int[]{1}));

        System.out.println(new Q84_LargestRectangleArea().largestRectangleArea2(new int[]{2,1,5,6,2,3}));
        System.out.println(new Q84_LargestRectangleArea().largestRectangleArea2(new int[]{1}));
    }

}

