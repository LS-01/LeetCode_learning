package com.ls.leetcode.done;

import com.ls.leetcode.general.GeneralUtil;

/**
 * 34
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * 1
 * 执行用时 : 0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗 : 42.3 MB, 在所有 Java 提交中击败了75.07%的用户
 *
 * 2
 * 执行用时 : 0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗 : 42.6 MB, 在所有 Java 提交中击败了62.57%的用户
 *
 */
public class Q34_SearchRange {

    public int[] searchRange1(int[] nums, int target) {
        return new int[]{searchStart(nums, target), searchEnd(nums, target)};
    }

    private int searchStart(int[] nums, int target){
        int le = 0;
        int ri = nums.length - 1;
        int mi = le + (ri - le) / 2;
        while (true){
            if(le > ri){
                return -1;
            }
            if(target < nums[le] || target > nums[ri]){
                return -1;
            }
            if(target < nums[mi]){
                ri = mi - 1;
            } else if(target == nums[mi]){
                ri = mi;
            } else {
                le = mi + 1;
            }
            mi = le + (ri - le) / 2;
            if(nums[mi] == target){
                if(mi - 1 >= 0){
                    if(nums[mi - 1] < target){
                        return mi;
                    } else {
                        ri--;
                        mi = le + (ri - le) / 2;
                        continue;
                    }
                }
                break;
            }
        }
        return mi;
    }

    private int searchEnd(int[] nums, int target){
        int le = 0;
        int ri = nums.length - 1;
        int mi = le + (ri - le) / 2;
        while (true){
            if(le > ri){
                return -1;
            }
            if(target < nums[le] || target > nums[ri]){
                return -1;
            }
            if(target < nums[mi]){
                ri = mi - 1;
            } else if(target == nums[mi]){
                le = mi;
            } else {
                le = mi + 1;
            }
            mi = le + (ri - le) / 2;
            if(nums[mi] == target){
                if(mi + 1 < nums.length){
                    if(nums[mi + 1] > target){
                        return mi;
                    } else {
                        le++;
                        mi = le + (ri - le) / 2;
                        continue;
                    }
                }
                break;
            }
        }
        return mi;
    }

    public int[] searchRange2(int[] nums, int target) {
        return new int[]{searchLowerBound(nums, target, 0, nums.length - 1), searchUpperBound(nums, target, 0, nums.length - 1)};
    }

    private int searchLowerBound(int[] nums, int target, int low, int high) {
        if(low > high){
            return -1;
        }

        int middle = low + (high - low) / 2;
        if(nums[middle] == target && (middle == 0 || nums[middle - 1] < target)){
            return middle;
        }

        if(nums[middle] >= target){
            return searchLowerBound(nums, target, low, middle - 1);
        } else {
            return searchLowerBound(nums, target, middle + 1, high);
        }
    }

    private int searchUpperBound(int[] nums, int target, int low, int high) {
        if(low > high){
            return -1;
        }

        int middle = low + (high - low) / 2;
        if(nums[middle] == target && (middle == nums.length - 1 || nums[middle + 1] > target)){
            return middle;
        }

        if(nums[middle] > target){
            return searchUpperBound(nums, target, low, middle - 1);
        } else {
            return searchUpperBound(nums, target, middle + 1, high);
        }
    }

    public static void main(String[] args) {
//        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange1(new int[]{5,7,7,8,8,10}, 8));
//        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange1(new int[]{5,7,7,8,8,10}, 6));
//        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange1(new int[]{5,7,7,8,8,10}, 5));
//        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange1(new int[]{5,7,7,8,8,10}, 10));
//        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange1(new int[]{5,7,7,7,7,10}, 7));
//        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange1(new int[]{5,6,7,8,9,10}, 7));

//        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange1(new int[]{}, 5));
        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange1(new int[]{2, 2}, 2));

//        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange2(new int[]{5,7,7,8,8,10}, 8));
//        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange2(new int[]{5,7,7,8,8,10}, 6));
//        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange2(new int[]{5,7,7,8,8,10}, 5));
//        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange2(new int[]{5,7,7,8,8,10}, 10));
//        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange2(new int[]{5,7,7,7,7,10}, 7));
//        GeneralUtil.printIntArray(new Q34_SearchRange().searchRange2(new int[]{5,6,7,8,9,10}, 7));
    }
}

