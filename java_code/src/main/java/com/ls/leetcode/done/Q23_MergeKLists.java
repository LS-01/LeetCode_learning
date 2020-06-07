package com.ls.leetcode.done;

import com.ls.leetcode.general.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * 1-1
 * 执行用时 : 1 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗 : 41.6 MB, 在所有 Java 提交中击败了52.84%的用户
 *
 * 1-2
 * 执行用时 : 4 ms, 在所有 Java 提交中击败了69.77%的用户
 * 内存消耗 : 42.7 MB, 在所有 Java 提交中击败了40.49%的用户
 *
 * 2
 * 执行用时 : 5 ms, 在所有 Java 提交中击败了61.64%的用户
 * 内存消耗 : 41.7 MB, 在所有 Java 提交中击败了50.94%的用户
 *
 */
public class Q23_MergeKLists {

    public ListNode mergeKLists1(ListNode[] lists) {
        if(lists.length <= 0){
            return null;
        }
        return mergeLists(lists, 0, lists.length - 1);
    }

    /**
     * 分治法
     * 时间复杂度**：O(nk×log(k))。
     * 空间复杂度**：O(1)。
     */

    private ListNode mergeLists(ListNode[] lists, int low, int high) {
        if(high == low){
            return lists[low];
        }
        int mi = low + (high - low) / 2;
        return merge1(mergeLists(lists, low, mi), mergeLists(lists, mi + 1, high));
//        return merge2(mergeLists(lists, low, mi), mergeLists(lists, mi + 1, high));
    }

    private ListNode merge1(ListNode ln1, ListNode ln2){
        if(ln1 == null){
            return ln2;
        }
        if(ln2 == null){
            return ln1;
        }

        ListNode ln1pre = new ListNode(0);
        ln1pre.next = ln1;
        ListNode ln2pre = new ListNode(0);
        ln2pre.next = ln2;

        ListNode ln = ln1pre;

        while(ln1 != null && ln2 != null){
            if(ln1.val <= ln2.val){
                ln1 = ln1.next;
                ln1pre = ln1pre.next;
            } else {
                ln1pre.next = ln2;
                while (ln2.next != null && ln2.next.val <= ln1.val){
                    ln2 = ln2.next;
                    ln1pre = ln1pre.next;
                }
                ln2pre.next = ln2.next;
                ln1pre = ln1pre.next;
                ln1pre.next = ln1;
                ln2 = ln2pre.next;
            }
        }
        if(ln1 == null){
            ln1pre.next = ln2;
        }
        return ln.next;
    }

    private ListNode merge2(ListNode a, ListNode b) {
        if (a == null) return b;
        if (b == null) return a;

        if (a.val <= b.val) {
            a.next = merge2(a.next, b);
            return a;
        }

        b.next = merge2(a, b.next);
        return b;
    }

    /**
     * 最小堆法
     * 时间复杂度就是 O(nk×log(k))。
     *
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists.length <= 0){
            return null;
        }
        ListNode fakeHead = new ListNode(0), p = fakeHead;
        int k = lists.length;

        PriorityQueue<ListNode> heap = new PriorityQueue<>(k, new Comparator<ListNode>() {
            public int compare(ListNode a, ListNode b) {
                return a.val - b.val;
            }
        });

        for (int i = 0; i < k; i++) {
            if (lists[i] != null) {
                heap.offer(lists[i]);
            }
        }

        while (!heap.isEmpty()) {
            ListNode node = heap.poll();

            p.next = node;
            p = p.next;

            if (node.next != null) {
                heap.offer(node.next);
            }
        }
        return fakeHead.next;
    }

    public static void main(String[] args) {
        ListNode.printList(new Q23_MergeKLists().mergeKLists1(new ListNode[]{
                ListNode.arrayToList(new int[]{1, 4, 5}),
                ListNode.arrayToList(new int[]{1, 3, 4}),
                ListNode.arrayToList(new int[]{2, 6})
        }));
        ListNode.printList(new Q23_MergeKLists().mergeKLists1(new ListNode[]{
                ListNode.arrayToList(new int[]{1}),
                ListNode.arrayToList(new int[]{0})
        }));

        ListNode.printList(new Q23_MergeKLists().mergeKLists2(new ListNode[]{
                ListNode.arrayToList(new int[]{1, 4, 5}),
                ListNode.arrayToList(new int[]{1, 3, 4}),
                ListNode.arrayToList(new int[]{2, 6})
        }));
    }

}
