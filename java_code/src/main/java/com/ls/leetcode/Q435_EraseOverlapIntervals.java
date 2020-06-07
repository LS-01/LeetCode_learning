package com.ls.leetcode;

/**
 * 435
 *
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * 输出: 1
 *
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: [ [1,2], [1,2], [1,2] ]
 *
 * 输出: 2
 *
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: [ [1,2], [2,3] ]
 *
 * 输出: 0
 *
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 */
public class Q435_EraseOverlapIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        return 0;
    }

}



















/**
 * LeetCode 第 435 题：给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 *
 *
 * 注意:
 *
 * 1. 可以认为区间的终点总是大于它的起点。
 * 2. 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 *
 *
 * **示例 1**
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * 输出: 1
 *
 *
 *
 * **解释**: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 *
 *
 * **示例 2**
 *
 * 输入: [ [1,2], [1,2], [1,2] ]
 *
 * 输出: 2
 *
 *
 *
 * **解释**: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 *
 *
 *
 * **示例 3**
 *
 * 输入: [ [1,2], [2,3] ]
 *
 * 输出: 0
 *
 *
 *
 * **解释**: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * ## 解题思路一： 暴力法
 *
 * 这道题是上一道题的一种变形，暴力法就是将各个区间按照起始时间的先后顺序排序，然后找出所有的组合，最后对每种组合分别判断各个区间有没有互相重叠。
 *
 * ### 算法分析
 *
 * 1. 排序需要 O(nlog(n)) 的时间复杂度。
 * 2. 找出所有组合，按照前一节课里提到的从一个字符串里找出所有子序列的组合个数的方法，取出 n 个区间，有 Cnn 种，算上空的集合，那么一共有 Cn0 + Cn1 + Cn2 + … Cnn = 2n。
 * 3. 对每种组合进行判断是否重叠，k 个区间，需要 O(k) 的时间复杂度。
 * 4. 总体时间复杂度为 Cn0 x 0 + Cn1×1 + Cn2×2 + … + Cnk * k + … + Cnn×n = n×2n-1。
 *
 * 由于 n×2n-1 已经远大于 nlog(n)，所以排序的时间复杂度就可以忽略不计，整体的时间复杂度就是 O(n×2n)。
 *
 *
 *
 * 建议：一定要记一些常见的时间复杂度计算公式，对于在面试中能准确快速地分析复杂度是非常有帮助的。
 *
 *
 *
 * ##
 *
 * ###### 解题思路二：另一种暴力法
 *
 * 对于暴力法，还有另外的分析方法。用两个变量 prev 和 curr 分别表示前一个区间和当前区间。
 *
 * 1. 如果当前区间和前一个区间没有发生重叠，则尝试保留当前区间，表明此处不需要删除操作。
 * 2. 题目要求最少的删除个数，只有在这样的情况下，才不需要做任何删除操作。
 * 3. 在这种情况下，虽然两个区间没有重叠，但是也要考虑尝试删除当前区间的情况。
 * 4. 对比哪种情况所需要删除的区间最少。
 *
 *
 *
 * 举例：有如下的几个区间 A、B、C，其中 A 是前一个区间，B 是当前区间，A 和 B 没有重叠。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/90/FB/CgoB5l2IeomAJypIAAA1_CqUqGA909.png)
 *
 * 1. 如果只考虑保留 B 的情况，而不考虑把 B 删除的情况，那么就会错过一个答案，因为在这个情况下，把 B 删除，只剩下 A 和 C，它们互不重叠，也能得到最优的解。
 *
 *
 *
 * 2. 遇到 A 和 B 相互重叠的情况时，必须要考虑把 B 删除掉。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/1B/CgotOV2IeomAasCyABO9nAPnBLM742.gif)
 *
 * 为什么不把 A 删除呢？因为如果把 A 删了，B 和 C 还是可能会重叠，则需要删除掉更多的区间，不满足题目要求。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/90/FB/CgoB5l2IeoqAMYGUAB4TTFLJ7aA153.gif)
 *
 * ### 代码实现
 *
 * ##
 *
 * ```
 * // 在主体函数里，先将区间按照起始时间的先后顺序排序，然后调用递归函数
 * int eraseOverlapIntervals(int[][] intervals) {
 *     Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
 *     return eraseOverlapIntervals(-1, 0, intervals);
 * }
 *
 * // 递归函数里，先检查是否已经处理完所有的区间，是，表明不需要删除操作，直接返回
 * int eraseOverlapIntervals(int prev, int curr, int[][] intervals) {
 *     if (curr == intervals.length) {
 *         return 0;
 *     }
 *
 *     int taken = Integer.MAX_VALUE, nottaken;
 *
 *     if (prev == -1 || intervals[prev][1] <= intervals[curr][0]) {
 *         // 只有当prev, curr没有发生重叠的时候，才可以选择保留当前的区间curr
 *         taken = eraseOverlapIntervals(curr, curr + 1, intervals);
 *     }
 *
 *     // 其他情况，可以考虑删除掉curr区间，看看删除了它之后会不会产生最好的结果
 *     nottaken = eraseOverlapIntervals(prev, curr + 1, intervals) + 1;
 *
 *     return Math.min(taken, nottaken);
 *
 * }
 * ```
 *
 * ###### 解题思路二：贪婪法
 *
 * ### 按照起始时间排序
 *
 * **举例**：有四个区间 A，B，C，D，A 跨度很大，B 和 C 重叠，C 和 D 重叠，而 B 和 D 不重叠。
 *
 *
 *
 * **解法**：要尽可能少得删除区间，那么当遇到了重叠的时候，应该把区间跨度大，即结束比较晚的那个区间删除。因为如果不删除它，它会和剩下的其他区间发生重叠的可能性非常大。
 *
 *
 *
 * 当发现 A 和 B 重叠，如果不删除 A，就得牺牲 B，C，D，而正确的答案是只需要删除 A 和 C 即可。
 *
 *
 *
 * 按照上述思想求解，实现过程如下。
 *
 *
 *
 * \1. A 和 B 重叠，由于 A 结束得比较晚，此时删除区间 A，保留区间 B。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/90/FB/CgoB5l2IeouALsHDACoIa6RqiFk139.gif)
 *
 * \2. B 和 C 重叠，由于 C 结束得晚，把区间 C 删除，保留区间 B。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/1B/CgotOV2IeouAVgkTACrf8XPAR8o811.gif)
 *
 * \3. B 和 D 不重叠，结束，一共只删除了 2 个区间。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/90/FB/CgoB5l2IeoyAS7VEABGI5Z_ovpM719.gif)
 *
 *
 *
 * **代码实现**
 *
 * ```
 * int eraseOverlapIntervals(int[][] intervals) {
 *     if (intervals.length == 0) return 0;
 *
 *     // 将所有区间按照起始时间排序
 *     Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
 *
 *     // 用一个变量 end 记录当前的最小结束时间点，以及一个 count 变量记录到目前为止删除掉了多少区间
 *     int end = intervals[0][1], count = 0;
 *
 *     // 从第二个区间开始，判断一下当前区间和前一个区间的结束时间
 *     for (int i = 1; i < intervals.length; i++) {
 *         // 当前区间和前一个区间有重叠，即当前区间的起始时间小于上一个区间的结束时间，end记录下两个结束时间的最小值，把结束时间晚的区间删除，计数加1。
 *         if (intervals[i][0] < end) {
 *             end = Math.min(end, intervals[i][1]);
 *             count++;
 *         } else {
 *             end = intervals[i][1];
 *         }
 *     }
 *
 *     // 如果没有发生重叠，根据贪婪法，更新 end 变量为当前区间的结束时间
 *     return count;
 *
 * }
 * ```
 *
 *
 *
 * ### 按照结束时间排序
 *
 * **题目演变**：在给定的区间中，最多有多少个区间相互之间是没有重叠的？
 *
 *
 *
 * 思路：假如求出了最多有 m 个区间是互相之间没有重叠的，则最少需要将 n−m 个区间删除才行。即，删掉“害群之马”，则剩下的就不会互相冲突了。
 *
 *
 *
 * 为什么按照结束时间排序会有助于我们统计出没有重叠的区间最大个数呢？举例说明如下。
 *
 *
 *
 * 假设今天有很多活动要举行，每个活动都有固定的时间，选择哪些活动，才能使参加的活动最多，然后在时间上不会互相重叠呢？
 *
 *
 *
 * 如果我们按照活动的起始时间去挑选，某个活动虽然开始得早，但是很有可能会持续一整天，就没有时间去参加其他活动了。如果按照活动的结束时间选，先挑那些最早结束的，就会尽可能节省出更多的时间来参加更多的活动。
 *
 *
 *
 * 根据这个思路，这道题也可以按照结束时间排序处理，于是，区间的顺序就是 {B, C, D, A}。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/1B/CgotOV2IeoyAMCcNAABEWLc4h90512.png)
 *
 *
 *
 * 实现：目标就是统计有多少个没有重叠的情况发生。若当前的区间和前一个区间没有重叠的时候，计数器加 1，同时，用当前的区间去和下一个区间比较。
 *
 *
 *
 * **代码实现**
 *
 * ```
 * int eraseOverlapIntervals(int[][] intervals) {
 *     if (intervals.length == 0) return 0;
 *
 *     // 按照结束时间将所有区间进行排序
 *     Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[1], i2[1]));
 *
 *     // 定义一个变量 end 用来记录当前的结束时间，count 变量用来记录有多少个没有重叠的区间
 *     int end = intervals[0][1], count = 1;
 *
 *     // 从第二个区间开始遍历剩下的区间
 *     for (int i = 1; i < intervals.length; i++) {
 *         // 当前区间和前一个结束时间没有重叠，那么计数加 1，同时更新一下新的结束时间
 *         if (intervals[i][0] >= end) {
 *             end = intervals[i][1];
 *             count++;
 *         }
 *     }
 *
 *     // 用总区间的个数减去没有重叠的区间个数，即为最少要删除掉的区间个数
 *     return intervals.length - count;
 * }
 * ```
 *
 * 关于区间的问题，LeetCode 上还有很多类似的题目，大家一定要去做做。
 */