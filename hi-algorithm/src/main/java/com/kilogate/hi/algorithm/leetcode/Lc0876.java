package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.util.ListNode;

/**
 * 链表的中间结点
 * <p>
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 *
 * @author kilogate
 * @create 2022/3/1 01:19
 **/
public class Lc0876 {
    public static void main(String[] args) {
        Lc0876 lc0876 = new Lc0876();

        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);

        ListNode res = lc0876.middleNode(listNode1);
        System.out.println(res);
    }

    public ListNode middleNode(ListNode head) {
        ListNode a = head, b = head;

        while (a != null && a.next != null) {
            a = a.next.next;
            b = b.next;
        }

        return b;
    }
}