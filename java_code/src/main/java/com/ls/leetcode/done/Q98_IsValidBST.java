package com.ls.leetcode.done;

import com.ls.leetcode.general.TreeNode;

import java.util.Stack;

/**
 * 98
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 执行用时： 7 ms , 在所有 Java 提交中击败了 5.78% 的用户
 * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 91.01% 的用户
 *
 */
public class Q98_IsValidBST {

    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        Stack<TreeNode> treeNodeStack = new Stack<>();
        Stack<Boolean> treeNodeVisitedStack = new Stack<>();
        treeNodeStack.push(root);
        treeNodeVisitedStack.push(false);
        TreeNode node = null;
        boolean nodeVisited = false;
        Integer val = null;
        while (!treeNodeStack.empty()){
            node = treeNodeStack.pop();
            nodeVisited = treeNodeVisitedStack.pop();
            if(nodeVisited || (node.left == null && node.right == null)){
                if(val == null || val < node.val){
                    val = node.val;
                } else {
                    return false;
                }
            } else {
                if(node.right != null){
                    treeNodeStack.push(node.right);
                    treeNodeVisitedStack.push(false);
                }
                treeNodeStack.push(node);
                treeNodeVisitedStack.push(true);
                if(node.left != null){
                    treeNodeStack.push(node.left);
                    treeNodeVisitedStack.push(false);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Q98_IsValidBST().isValidBST(TreeNode.trans(new Integer[]{2,1,3})));
        System.out.println(new Q98_IsValidBST().isValidBST(TreeNode.trans(new Integer[]{5,1,4,null,null,3,6})));
    }

}
