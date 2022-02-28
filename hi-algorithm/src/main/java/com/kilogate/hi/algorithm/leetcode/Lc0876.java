package com.kilogate.hi.algorithm.leetcode;

/**
 * 链表的中间结点
 * <p>
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 * @author fengquanwei
 * @create 2022/3/1 01:19
 **/
public class Lc0876 {
    public static void main(String[] args) {
        Lc0876 lc0876 = new Lc0876();

//        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4, null);
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

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();

            ListNode node = this;
            while (node != null) {
                res.append(node.val).append("->");
                node = node.next;
            }

            return res.substring(0, res.length() - 2);
        }
    }
}