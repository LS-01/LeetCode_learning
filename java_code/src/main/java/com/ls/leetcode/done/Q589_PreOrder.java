package com.ls.leetcode.done;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 589
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 *
 * 例如，给定一个 3叉树 :
 *
 *          1
 *      3   2   4
 *     5 6
 *
 * 返回其前序遍历: [1,3,5,6,2,4]。
 *
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 *
 * 递归法
 * 执行用时： 1 ms , 在所有 Java 提交中击败了 96.39% 的用户
 * 内存消耗： 40.7 MB , 在所有 Java 提交中击败了 42.31% 的用户
 *
 * 迭代法
 * 执行用时： 4 ms , 在所有 Java 提交中击败了 39.16% 的用户
 * 内存消耗： 40.3 MB , 在所有 Java 提交中击败了 82.35% 的用户
 *
 */
public class Q589_PreOrder {

    //递归法
    public List<Integer> preorder1(Node root) {
        List<Integer> nodeVals = new ArrayList<>();
        preorder1(root, nodeVals);
        return nodeVals;
    }

    private void preorder1(Node node, List<Integer> nodeVals){
        if(node == null){
            return;
        }
        nodeVals.add(node.val);
        if(node.children != null){
            for (int i = 0; i < node.children.size(); i++) {
                preorder1(node.children.get(i), nodeVals);
            }
        }
    }

    //迭代法
    public List<Integer> preorder2(Node root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<Integer> nodeVals = new ArrayList<>();
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(root);
        Node node = null;
        while (!nodeStack.empty()){
            node = nodeStack.pop();
            nodeVals.add(node.val);
            if(node.children != null){
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    nodeStack.push(node.children.get(i));
                }
            }
        }
        return nodeVals;
    }

    public static void main(String[] args) {
        List<Node> nodes1 = new ArrayList<>();
        nodes1.add(new Node(5));
        nodes1.add(new Node(6));

        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(new Node(3, nodes1));
        nodes2.add(new Node(2));
        nodes2.add(new Node(4));

        Node root = new Node(1, nodes2);
        List<Integer> integers = new Q589_PreOrder().preorder1(root);
        for (int i = 0; i < integers.size(); i++) {
            System.out.println(integers.get(i));
        }

        integers = new Q589_PreOrder().preorder2(root);
        for (int i = 0; i < integers.size(); i++) {
            System.out.println(integers.get(i));
        }
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
