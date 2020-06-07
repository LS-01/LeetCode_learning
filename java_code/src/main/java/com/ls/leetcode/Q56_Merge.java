package com.ls.leetcode;

import com.ls.leetcode.general.GeneralUtil;

/**
 * 56
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 */
public class Q56_Merge {

    public int[][] merge(int[][] intervals) {
        return null;
    }

    public static void main(String[] args) {
        GeneralUtil.printDoubleIntArray(new Q56_Merge().merge(new int[][]{
                {1,3},
                {2,6},
                {8,10},
                {15,18}
        }));

        GeneralUtil.printDoubleIntArray(new Q56_Merge().merge(new int[][]{
                {1,4},
                {4,5}
        }));
    }

}






















/*
 * 这个就是贪婪算法。
 * ```
 * int[][] merge(int[][] intervals) {
 *     // 将所有的区间按照起始时间的先后顺序排序
 *     Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
 *
 *     // 定义一个 previous 变量，初始化为 null
 *     int[] previous = null;
 *     // 定义一个 result 变量，用来保存最终的区间结果
 *     List<int[]> result = new ArrayList<>();
 *
 *     // 从头开始遍历给定的所有区间
 *     for (int[] current : intervals) {
 *         // 如果这是第一个区间，或者当前区间和前一个区间没有重叠，那么将当前区间加入到结果中
 *         if (previous == null || current[0] > previous[1]) {
 *             result.add(previous = current);
 *         } else { // 否则，两个区间发生了重叠，更新前一个区间的结束时间
 *             prev[1] = Math.max(previous[1], current[1]);
 *         }
 *     }
 *
 *     return result.toArray(new int[result.size()][]);
 *  }
 * ```
 *
 * 时间复杂度 O(nlog(n))，空间复杂度为 O(n)
 */