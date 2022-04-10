package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.util.ListNode;

/**
 * K 个一组翻转链表
 * <p>
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 *
 * @author fengquanwei
 * @create 2022/3/6 22:49
 **/
public class Lc0025 {
    public static void main(String[] args) {
        Lc0025 lc0025 = new Lc0025();
        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode res = lc0025.reverseKGroup(root, 2);
        System.out.println(res);
    }

    public ListNode reverseKGroup(ListNode root, int k) {
        if (root == null || k <= 1) {
            return root;
        }

        ListNode res = null;
        ListNode head = root, tail = root;
        ListNode prevTail = null;
        int count = 1;
        while (tail != null) {
            tail = tail.next;
            count++;

            if (count == k && tail != null) {
                reverse(prevTail, head, tail);

                prevTail = head;

                if (res == null) {
                    res = tail;
                }

                head = head.next;
                tail = head;
                count = 1;
            }
        }

        return res;
    }

    private void reverse(ListNode prevTail, ListNode head, ListNode tail) {
        ListNode prev = tail.next, curr = head, stop = tail.next;

        while (curr != null && curr != stop) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        if (prevTail != null) {
            prevTail.next = tail;
        }
    }
}
