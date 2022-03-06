package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.leetcode.node.ListNode;

/**
 * 删除链表的倒数第 N 个结点
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * @author fengquanwei
 * @create 2022/3/5 21:53
 **/
public class Lc0019 {
    public static void main(String[] args) {
        Lc0019 lc0019 = new Lc0019();
        ListNode listNode5 = new ListNode(5, null);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        ListNode res = lc0019.removeNthFromEnd(listNode1, 2);
        System.out.println(res);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode curr = head;

        ListNode target = null;
        ListNode prev = null;

        int gap = 1;
        while (curr != null) {
            if (gap == n) {
                prev = target;
                target = target == null ? head : target.next;
            } else {
                gap++;
            }

            curr = curr.next;
        }

        if (target == null) {
            return head;
        }

        if (prev == null) {
            return head.next;
        }

        prev.next = target.next;
        return head;
    }
}
