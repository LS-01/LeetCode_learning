package com.ls.leetcode.done;

import com.ls.leetcode.general.GeneralUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 39
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * 回溯
 *
 * 执行用时 : 4 ms ,在所有 Java 提交中击败了74.40%的用户
 * 内存消耗 : 41.3 MB ,在所有 Java 提交中击败了5.99%的用户
 *
 */
public class Q39_CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        cal(0, candidates, target, new ArrayList<Integer>(), lists);
        return lists;
    }

    private void cal(int start, int[] candidates, int target, ArrayList<Integer> li, List<List<Integer>> lists){
        if(target < 0){
            return;
        }

        if(0 == target){
            lists.add((List<Integer>) li.clone());
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            li.add(candidates[i]);
            cal(i, candidates, target - candidates[i], li, lists);
            li.remove(li.size() - 1);
        }
    }

    public static void main(String[] args) {
        GeneralUtil.printDoubleIntegerList(new Q39_CombinationSum().combinationSum(new int[]{2,3,6,7}, 7));
        GeneralUtil.printDoubleIntegerList(new Q39_CombinationSum().combinationSum(new int[]{2,3,5}, 8));
    }
}
