package com.ls.leetcode.done;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 239
 *
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *  
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 *  
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 * 执行用时 : 18 ms, 在所有 Java 提交中击败了42.09%的用户
 * 内存消耗 : 49.5 MB, 在所有 Java 提交中击败了5.02%的用户
 */
public class Q239_MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k <= 0){
            return new int[0];
        }
        int[] maxVal = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if(deque.size() > 0){
                int max = deque.peekLast();
                while(max >= 0 && (max <= i - k || nums[i] >= nums[max])){
                    deque.removeLast();
                    if(deque.size() > 0){
                        max = deque.peekLast();
                    } else {
                        max = -1;
                    }
                }
            }
            if(deque.size() > 0){
                int min = deque.peekFirst();
                while (min >= 0 && nums[min] < nums[i]){
                    deque.removeFirst();
                    if(deque.size() > 0){
                        min = deque.peekFirst();
                    } else {
                        min = -1;
                    }
                }
            }
            deque.addFirst(i);

            if(i + 1 >= k){
                maxVal[i - k + 1] = nums[deque.peekLast()];
            }
        }
        return maxVal;
    }

    public static void main(String[] args) {
        int[] maxVal = new Q239_MaxSlidingWindow().maxSlidingWindow(new int[]{1,-1}, 1);
//        int[] maxVal = new Q239_MaxSlidingWindow().maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3);
//        int[] maxVal = new Q239_MaxSlidingWindow().maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
//        int[] maxVal = new Q239_MaxSlidingWindow().maxSlidingWindow(new int[]{}, 0);
//        int[] maxVal = new Q239_MaxSlidingWindow().maxSlidingWindow(new int[]{3,1,3,1,3,1,3}, 3);
//        int[] maxVal = new Q239_MaxSlidingWindow().maxSlidingWindow(new int[]{3,1,3,1,3,1,3}, 1);
        for (int i = 0; i < maxVal.length; i++) {
            System.out.println(maxVal[i]);
        }
    }

}
