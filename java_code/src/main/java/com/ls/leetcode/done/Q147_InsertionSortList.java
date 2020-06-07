package com.ls.leetcode.done;

import com.ls.leetcode.general.ListNode;

/**
 * 147
 *
 * 对链表进行插入排序。
 *
 *
 * 从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 *  
 *
 * 插入排序算法：
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *  
 *
 * 示例 1：
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * 执行用时 : 34 ms, 在所有 Java 提交中击败了9.83%的用户
 * 内存消耗 : 41.4 MB, 在所有 Java 提交中击败了5.06%的用户
 */
public class Q147_InsertionSortList {

    public ListNode insertionSortList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode ahead = new ListNode(0);
        ahead.next = head;

        ListNode preLn = head;
        ListNode ln = preLn.next;
        while (ln != null){
//            ListNode.printList(ahead.next);
            ListNode preVisit = ahead;
            ListNode visit = preVisit.next;
            while(visit != null && visit.val <= ln.val){
                if(visit == ln){
                    visit = null;
                    break;
                }
                preVisit = preVisit.next;
                visit = visit.next;
            }
            if(visit != null){
                preLn.next = ln.next;
                preVisit.next = ln;
                ln.next = visit;
                ln = preLn.next;
            } else {
                preLn = preLn.next;
                ln = ln.next;
            }
        }
        return ahead.next;
    }

    public void test(int[] array){
        ListNode ln = ListNode.arrayToList(array);
        ListNode.printList(ln);
        ListNode.printList(insertionSortList(ln));
    }

    public static void main(String[] args) {
        Q147_InsertionSortList is = new Q147_InsertionSortList();
        is.test(new int[]{4, 2, 1, 3});
        is.test(new int[]{-1, 5, 3, 4, 0});
        is.test(new int[]{0});
        is.test(new int[]{});
        is.test(new int[]{-1, 6});
    }

}
