package com.ls.leetcode.done;

/**
 * 33
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * 1
 * 执行用时 : 0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗 : 37.9 MB, 在所有 Java 提交中击败了36.22%的用户
 *
 * 2
 * 执行用时 : 1 ms, 在所有 Java 提交中击败了68.28%的用户
 * 内存消耗 : 38.1 MB, 在所有 Java 提交中击败了29.88%的用户
 *
 */
public class Q33_Search {

    public int search1(int[] nums, int target) {
        int le = 0;
        int ri = nums.length - 1;
        if(ri < 0){
            return -1;
        }
        int mi = le + (ri - le) / 2;
        while (nums[mi] != target){
            if(nums[le] <= nums[mi]){
                if(target < nums[le] || target > nums[mi]){
                    le = mi + 1;
                } else {
                    ri = mi - 1;
                }
            } else {
                if(target < nums[mi] || target > nums[ri]){
                    ri = mi - 1;
                } else {
                    le = mi + 1;
                }
            }
            mi = le + (ri - le) / 2;
            if(mi >= nums.length || (le == ri && nums[le] != target)){
                return -1;
            }
        }
        return mi;
    }

    public int search2(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public int binarySearch(int[] nums, int target, int low, int high) {
        if(low > high){
            return -1;
        }

        int middle = low + (high - low) / 2;
        if(nums[middle] == target){
            return middle;
        }

        if (nums[low] <= nums[middle]) {
            if (nums[low] <= target && target < nums[middle]) {
                return binarySearch(nums, target, low, middle - 1);
        }
            return binarySearch(nums, target, middle + 1, high);
        } else {
            if (nums[middle] < target && target <= nums[high]) {
                return binarySearch(nums, target, middle + 1, high);
        }
            return binarySearch(nums, target, low, middle - 1);
        }
    }

    public static void main(String[] args) {
//        System.out.println(new Q33_Search().search1(new int[]{4,5,6,7,0,1,2}, 0));
//        System.out.println(new Q33_Search().search1(new int[]{4,5,6,7,0,1,2}, 3));
//        System.out.println(new Q33_Search().search1(new int[]{4,5,6,7,0,1,2}, 7));
//        System.out.println(new Q33_Search().search1(new int[]{0,1,2,4,5,6,7}, 3));
//        System.out.println(new Q33_Search().search1(new int[]{0,1,2,4,5,6,7}, 0));
//
//        System.out.println(new Q33_Search().search1(new int[]{}, 1));
//        System.out.println(new Q33_Search().search1(new int[]{3, 1}, 1));
//        System.out.println(new Q33_Search().search1(new int[]{1}, 0));
//
//        System.out.println(new Q33_Search().search2(new int[]{4,5,6,7,0,1,2}, 0));
//        System.out.println(new Q33_Search().search2(new int[]{4,5,6,7,0,1,2}, 3));
//        System.out.println(new Q33_Search().search2(new int[]{4,5,6,7,0,1,2}, 7));
//        System.out.println(new Q33_Search().search2(new int[]{0,1,2,4,5,6,7}, 3));
//        System.out.println(new Q33_Search().search2(new int[]{0,1,2,4,5,6,7}, 0));

        System.out.println(new Q33_Search().search2(new int[]{4,5,6,7,8,1,2,3}, 8));
    }
}
