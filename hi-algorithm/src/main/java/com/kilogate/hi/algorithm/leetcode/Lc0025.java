package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.leetcode.node.ListNode;

/**
 * K 个一组翻转链表
 * <p>
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
