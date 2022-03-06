package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.leetcode.node.ListNode;

/**
 * 反转链表
 * <p>
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * @author fengquanwei
 * @create 2022/3/2 00:05
 **/
public class Lc0206 {
    public static void main(String[] args) {
        Lc0206 lc0206 = new Lc0206();
        ListNode listNode5 = new ListNode(5, null);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        ListNode res = lc0206.reverseList(listNode1);
        System.out.println(res);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
