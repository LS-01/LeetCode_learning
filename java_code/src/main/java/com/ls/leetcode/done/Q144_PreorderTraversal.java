package com.ls.leetcode.done;

import com.ls.leetcode.general.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 递归法
 * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
 * 内存消耗： 38 MB , 在所有 Java 提交中击败了 57.83% 的用户
 *
 * 迭代法
 * 执行用时： 1 ms , 在所有 Java 提交中击败了 46.55% 的用户
 * 内存消耗： 38 MB , 在所有 Java 提交中击败了 42.15% 的用户
 *
 */
public class Q144_PreorderTraversal {

    //递归法
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> nodeVals = new ArrayList<>();
        preorderTraversal1(root, nodeVals);
        return nodeVals;
    }

    public void preorderTraversal1(TreeNode node, List<Integer> nodeVals){
        if(node == null){
            return;
        }
        nodeVals.add(node.val);
        if(node.left != null){
            preorderTraversal1(node.left, nodeVals);
        }
        if(node.right != null){
            preorderTraversal1(node.right, nodeVals);
        }
    }

    //迭代法
    public List<Integer> preorderTraversal2(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<Integer> nodeVals = new ArrayList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        TreeNode node = null;
        while (!nodeStack.empty()){
            node = nodeStack.pop();
            nodeVals.add(node.val);
            if(node.right != null){
                nodeStack.push(node.right);
            }
            if(node.left != null){
                nodeStack.push(node.left);
            }
        }
        return nodeVals;
    }

    public static void main(String[] args) {
        List<Integer> integers = new Q144_PreorderTraversal().preorderTraversal1(TreeNode.trans(new Integer[]{1,null,2,3}));
        for (int i = 0; i < integers.size(); i++) {
            System.out.println(integers.get(i));
        }

        integers = new Q144_PreorderTraversal().preorderTraversal2(TreeNode.trans(new Integer[]{1,null,2,3}));
        for (int i = 0; i < integers.size(); i++) {
            System.out.println(integers.get(i));
        }
    }
}
