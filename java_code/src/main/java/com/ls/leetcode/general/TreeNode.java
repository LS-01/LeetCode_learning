package com.ls.leetcode.general;

import java.util.Stack;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public static TreeNode trans(Integer[] nums){
        if(nums.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(nums[0]);
        Stack<TreeNode> treeNodeStack = new Stack<>();
        treeNodeStack.push(root);
        int i = 1;
        while (i < nums.length){
            TreeNode node = treeNodeStack.pop();
            TreeNode leftNode = null;
            if(nums[i] != null){
                leftNode = new TreeNode(nums[i]);
                node.left = leftNode;
            }
            i++;
            if (i < nums.length && nums[i] != null){
                TreeNode rightNode = new TreeNode(nums[i]);
                node.right = rightNode;
                treeNodeStack.push(rightNode);
            }
            i++;
            if(leftNode != null){
                treeNodeStack.push(leftNode);
            }
        }
        return root;
    }
}
