package com.ls.leetcode.general;

public class ListNode {

    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
    }

    public static ListNode arrayToList(int[] array){
        if(array.length <= 0){
            return null;
        }
        ListNode root = new ListNode(array[0]);
        ListNode l = root;
        for (int i = 1; i < array.length; i++) {
            l.next = new ListNode(array[i]);
            l = l.next;
        }
        return root;
    }

    public static void printList(ListNode ln){
        StringBuilder res = new StringBuilder();
        while(ln != null){
            res.append(ln.val);
            if(ln.next != null){
                res.append(" -> ");
            }
            ln = ln.next;
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        ListNode.printList(arrayToList(new int[]{1, 2, 3, 4, 5}));
    }
}
