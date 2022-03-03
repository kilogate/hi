package com.kilogate.hi.algorithm.leetcode;

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
        ListNode listNode5 = lc0206.new ListNode(5, null);
        ListNode listNode4 = lc0206.new ListNode(4, listNode5);
        ListNode listNode3 = lc0206.new ListNode(3, listNode4);
        ListNode listNode2 = lc0206.new ListNode(2, listNode3);
        ListNode listNode1 = lc0206.new ListNode(1, listNode2);
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

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();

            ListNode curr = this;
            while (curr != null) {
                res.append(curr.val).append("->");
                curr = curr.next;
            }

            return res.substring(0, res.length() - 2);
        }
    }
}
