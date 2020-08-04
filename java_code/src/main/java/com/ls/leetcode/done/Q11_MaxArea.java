package com.ls.leetcode.done;

/**
 * 11
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 *
 * 输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例：
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 *
 * 执行用时： 2 ms , 在所有 Java 提交中击败了 99.21% 的用户
 * 内存消耗： 40 MB , 在所有 Java 提交中击败了 66.15% 的用户
 *
 */
public class Q11_MaxArea {

    public int maxArea(int[] height) {
        int max = 0;
        int temp = 0;
        int i = 0;
        int j = height.length - 1;
        while(j > i){
            if(height[i] > height[j]){
                temp = (j - i) * height[j];
                j--;
            } else {
                temp = (j - i) * height[i];
                i++;
            }
            if(temp > max){
                max = temp;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Q11_MaxArea().maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
