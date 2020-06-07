package com.ls.leetcode.done;

import com.ls.leetcode.general.GeneralUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 51
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 * 回溯
 *
 * 执行用时 : 14 ms, 在所有 Java 提交中击败了11.67%的用户
 * 内存消耗 : 41.6 MB, 在所有 Java 提交中击败了5.98%的用户
 *
 */
public class Q51_SolveNQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        handle(n, new ArrayList<Integer>(), solutions);
        return solutions;
    }

    private void handle(int n, List<Integer> solution, List<List<String>> solutions){
        if(solution.size() == n){
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                int lo = solution.get(i);
                for (int j = 0; j < n; j++) {
                    if(lo == j){
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                stringList.add(sb.toString());
            }
            solutions.add(stringList);
            return;
        }
        for (int i = 0; i < n; i++) {
            if(queenJudge(i, solution)){
                solution.add(i);
                handle(n, solution, solutions);
                solution.remove(solution.size() - 1);
            }
        }
    }

    private boolean queenJudge(int i, List<Integer> solution){
        int j = solution.size();
        for (int k = 0; k < solution.size(); k++) {
            if(i == solution.get(k) || i - solution.get(k) == j - k || i + j == solution.get(k) + k){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        GeneralUtil.printDoubleStringList(new Q51_SolveNQueens().solveNQueens(4));
    }
}
