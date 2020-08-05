package com.ls.leetcode.done;

import com.ls.leetcode.general.TreeNode;

import java.util.LinkedList;

/**
 * 104
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * 执行用时： 1 ms , 在所有 Java 提交中击败了 16.94% 的用户
 * 内存消耗： 39.4 MB , 在所有 Java 提交中击败了 91.05% 的用户
 *
 */
public class Q104_MaxDepth {

    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int maxDepth = 0;
        LinkedList<TreeNode> nodeList = new LinkedList<>();
        nodeList.add(root);
        TreeNode node = null;
        while (nodeList.size() > 0){
            maxDepth++;
            int length = nodeList.size();
            int i = 0;
            while (i < length){
                node = nodeList.getFirst();
                if(node.left != null){
                    nodeList.addLast(node.left);
                }
                if(node.right != null){
                    nodeList.addLast(node.right);
                }
                nodeList.removeFirst();
                i++;
            }
        }
        return maxDepth;
    }

    public static void main(String[] args) {
        System.out.println(new Q104_MaxDepth().maxDepth(TreeNode.trans(new Integer[]{3,9,20,null,null,15,7})));
    }
}
