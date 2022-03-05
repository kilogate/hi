package com.kilogate.hi.algorithm.leetcode;

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
        Lc0019.ListNode listNode5 = lc0019.new ListNode(5, null);
        Lc0019.ListNode listNode4 = lc0019.new ListNode(4, listNode5);
        Lc0019.ListNode listNode3 = lc0019.new ListNode(3, listNode4);
        Lc0019.ListNode listNode2 = lc0019.new ListNode(2, listNode3);
        Lc0019.ListNode listNode1 = lc0019.new ListNode(1, listNode2);
        Lc0019.ListNode res = lc0019.removeNthFromEnd(listNode1, 2);
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

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
