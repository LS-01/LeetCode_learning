package com.ls.leetcode.done;

/**
 * 215
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * 快速排序
 *
 * 执行用时 : 54 ms, 在所有 Java 提交中击败了7.57%的用户
 * 内存消耗 : 40.8 MB, 在所有 Java 提交中击败了5.09%的用户
 *
 */
public class Q215_FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        int p = -1;
        while (p != k - 1){
            if(p < k - 1){
                p = quickSort(nums, p + 1, nums.length - 1);
            } else {
                p = quickSort(nums, 0, p - 1);
            }
        }
        return nums[p];
    }

    private int quickSort(int[] nums, int le, int ri){
        while(le != ri){
            while (le < ri && nums[le] >= nums[ri]){
                le++;
            }
            if(le < ri){
                int temp = nums[le];
                nums[le] = nums[ri];
                nums[ri] = temp;
                temp = le;
                le = ri;
                ri = temp;
            }
            while (le > ri && nums[le] < nums[ri]){
                le--;
            }
            if(le > ri){
                int temp = nums[le];
                nums[le] = nums[ri];
                nums[ri] = temp;
                temp = le;
                le = ri;
                ri = temp;
            }
        }
        return le;
    }

    public static void main(String[] args) {
        System.out.println(new Q215_FindKthLargest().findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        System.out.println(new Q215_FindKthLargest().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
        System.out.println(new Q215_FindKthLargest().findKthLargest(new int[]{1,2,3,4,5,6,7,8,9}, 2));
    }

}
