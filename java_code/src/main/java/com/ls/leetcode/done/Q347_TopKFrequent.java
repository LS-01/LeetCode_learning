package com.ls.leetcode.done;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 347
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 *
 * 优先队列
 */
public class Q347_TopKFrequent {

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        PriorityBlockingQueue<List<Integer>> pq = new PriorityBlockingQueue<List<Integer>>(nums.length, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(1) - o2.get(1);
            }
        });
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i])){
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        Set<Integer> set = map.keySet();
        for (Integer i : set) {
            List<Integer> li = new ArrayList<>();
            li.add(i);
            li.add(map.get(i));
            pq.add(li);
        }
        while (k > 0){
            while(list.size() > 0 && pq.peek().get(0).equals(list.get(list.size() - 1))){
                pq.poll();
            }
            list.add(pq.poll().get(0));
            k--;
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new Q347_TopKFrequent().topKFrequent(new int[]{1,1,1,2,2,3,3,3,3,3}, 2).get(1));
        System.out.println(new Q347_TopKFrequent().topKFrequent(new int[]{1}, 1).get(0));
    }

}
