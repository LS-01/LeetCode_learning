package com.ls.leetcode.done;

import com.ls.leetcode.general.GeneralUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 40
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 执行用时 : 28 ms, 在所有 Java 提交中击败了11.36%的用户
 * 内存消耗 : 41.4 MB, 在所有 Java 提交中击败了7.50%的用户
 *
 */
public class Q40_CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Map<Integer, Integer>> cou = new ArrayList<>();
        cal(0, candidates, target, new ArrayList<Integer>(), lists, cou);
        return lists;
    }

    private void cal(int start, int[] candidates, int target, ArrayList<Integer> li, List<List<Integer>> lists, List<Map<Integer, Integer>> cou){
        if(target < 0){
            return;
        }

        if(0 == target){
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < li.size(); i++) {
                if(map.containsKey(li.get(i))){
                    map.put(li.get(i), map.get(li.get(i)) + 1);
                } else {
                    map.put(li.get(i), 1);
                }
            }
            int c = 0;
            for (int i = 0; i < cou.size(); i++) {
                c = 0;
                for (int j = 0; j < li.size(); j++) {
                    if(cou.get(i).containsKey(li.get(j))){
                        if(cou.get(i).get(li.get(j)).equals(map.get(li.get(j)))){
                            c++;
                        }
                    } else {
                        c = 0;
                        break;
                    }
                }
                if(c == li.size()){
                    return;
                }
            }
            if(c != li.size()){
                cou.add(map);
                lists.add((List<Integer>) li.clone());
            }
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            li.add(candidates[i]);
            cal(i + 1, candidates, target - candidates[i], li, lists, cou);
            li.remove(li.size() - 1);
        }
    }

    public static void main(String[] args) {
        GeneralUtil.printDoubleIntegerList(new Q40_CombinationSum2().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
        System.out.println();
        GeneralUtil.printDoubleIntegerList(new Q40_CombinationSum2().combinationSum2(new int[]{2,5,2,1,2}, 5));
    }

}
