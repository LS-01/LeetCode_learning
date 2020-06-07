package com.ls.leetcode.done;

import java.util.Stack;

/**
 * 230
 *
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 * 执行用时 :1 ms, 在所有 Java 提交中击败了73.84%的用户
 * 内存消耗 : 41.4 MB, 在所有 Java 提交中击败了5.09%的用户
 */
public class Q230_KthSmallest {

    public int kthSmallest(TreeNode root, int k) {
        if(root != null && k >= 1){
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while(stack.size() > 0){
                TreeNode tn = stack.pop();
                if(tn.left != null){
                    TreeNode temp = new TreeNode(tn.val);
                    temp.right = tn.right;
                    stack.push(temp);

                    TreeNode r = new TreeNode(tn.left.val);
                    r.right = tn.left.right;
                    r.left = tn.left.left;
                    stack.push(r);
                } else {
                    k--;
                    if(tn.right != null){
                        stack.push(tn.right);
                    }
                }
                if(k == 0){
                    return tn.val;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
//        Integer[] nodes = new Integer[]{3,1,4,null,2};
//        int k = 1;

//        Integer[] nodes = new Integer[]{5,3,6,2,4,null,null,1};
//        int k = 3;

//        Integer[] nodes = new Integer[]{1, null, 2};
//        int k = 2;

//        Integer[] nodes = new Integer[]{};
//        int k = 0;

//        Integer[] nodes = new Integer[]{1};
//        int k = 0;

        Integer[] nodes = new Integer[]{2, 1};
        int k = 2;

        System.out.println(new Q230_KthSmallest().kthSmallest(generalTree(nodes), k));
    }

    public static TreeNode generalTree(Integer[] nodes){
        if(nodes.length <= 0){
            return null;
        }
        TreeNode tn = new TreeNode(nodes[0]);
        getChildrenNode(nodes, 0, tn);
        return tn;
    }

    public static void getChildrenNode(Integer[] nodes, int i, TreeNode tn){
        int j1 = i * 2 + 1;
        int j2 = j1 + 1;
        if(j1 < nodes.length){
            if(nodes[j1] == null){
                tn.left = null;
            } else {
                TreeNode tl = new TreeNode(nodes[j1]);
                tn.left = tl;
                getChildrenNode(nodes, j1, tl);
            }
        }
        if(j2 < nodes.length){
            if(nodes[j2] == null){
                tn.right = null;
            } else {
                TreeNode tr = new TreeNode(nodes[j2]);
                tn.right = tr;
                getChildrenNode(nodes, j2, tr);
            }
        }
    }

    public static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        TreeNode(Integer x) { val = x; }
    }

}
