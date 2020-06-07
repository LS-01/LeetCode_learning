package com.ls.leetcode.undone.locked;

/**
 * 253 locked
 */
public class Q253_MeetingRoomsII {
}

































/**
 * ```
 * 贪婪算法
 * int minMeetingRooms(Interval[] intervals) {
 *     if (intervals == null || intervals.length == 0)
 *         return 0;
 *
 *     // 将输入的一系列会议按照会议的起始时间排序。
 *     Arrays.sort(intervals, new Comparator<Interval>() {
 *         public int compare(Interval a, Interval b) { return a.start - b.start; }
 *     });
 *
 *     // 用一个最小堆来维护目前开辟的所有会议室，最小堆里的会议室按照会议的结束时间排序。
 *     PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
 *         public int compare(Interval a, Interval b) { return a.end - b.end; }
 *     });
 *
 *     // 让第一个会议在第一个会议室里举行。
 *     heap.offer(intervals[0]);
 *
 *     for (int i = 1; i < intervals.length; i++) {
 *         // 从第二个会议开始，对于每个会议，我们都从最小堆里取出一个会议室，那么这个会议室里的会议一定是最早结束的。
 *         Interval interval = heap.poll();
 *
 *         if (intervals[i].start >= interval.end) {
 *         // 若当前要开的会议可以等会议室被腾出才开始，那么就可以重复利用这个会议室。
 *         interval.end = intervals[i].end;
 *       } else {
 *         // 否则，开一个新的会议室。
 *         heap.offer(intervals[i]);
 *     }
 *
 *     // 把旧的会议室也放入到最小堆里。
 *     heap.offer(interval);
 *     }
 *     // 最小堆里的会议室个数就是要求的答案，即最少的会议个数。
 *     return heap.size();
 * }
 * ```
 */