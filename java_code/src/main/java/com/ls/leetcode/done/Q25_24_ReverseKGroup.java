package com.ls.leetcode.done;

import com.ls.leetcode.general.ListNode;

/**
 * 25 -> 24
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例 :
 *
 * 给定这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 说明 :
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class Q25_24_ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if(k <= 1 || head == null){
            return head;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;

        ListNode pre = newHead;
        ListNode next = head.next;
        ListNode cur = head;

        int cou = 0;
        while(cur != null){
            cou++;
            cur = cur.next;
        }
        cou /= k;
        cur = head;

        int n = 0;
        while(cou > 0 && next != null){
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
            n++;
            if(n >= k - 1){
                n = 0;
                pre = cur;
                cur = cur.next;
                cou--;
            }
            if(cur != null){
                next = cur.next;
            }
        }

        return newHead.next;
    }

    public static void main(String[] args) {
        Q25_24_ReverseKGroup rk = new Q25_24_ReverseKGroup();

        ListNode ln = ListNode.arrayToList(new int[]{1, 2, 3, 4, 5});
        ListNode.printList(ln);

        ListNode n = rk.reverseKGroup(ln, 2);
        ListNode.printList(n);

    }
}