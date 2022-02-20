package com.kilogate.hi.algorithm.leetcode;

/**
 * 两数相加
 * <p>
 * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author fengquanwei
 * @create 2022/2/19 13:15
 **/
public class Lc0002 {
    public static void main(String[] args) {
        Lc0002 lc0002 = new Lc0002();
        ListNode listNode1 = lc0002.buildListNode(345);
        ListNode listNode2 = lc0002.buildListNode(678);
        ListNode result = lc0002.addTwoNumbers(listNode1, listNode2);
        System.out.println(result);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode tail = null;

        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 == null ? 0 : l1.val;
            int n2 = l2 == null ? 0 : l2.val;
            int sum = n1 + n2 + carry;

            int n = sum % 10;
            carry = sum / 10;

            ListNode newNode = new ListNode(n);

            if (result == null) {
                result = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (carry != 0) {
            ListNode newNode = new ListNode(carry);
            tail.next = newNode;
        }

        return result;
    }

    public ListNode buildListNode(int num) {
        if (num < 0) {
            return null;
        }

        if (num == 0) {
            return new ListNode(0);
        }

        ListNode result = null;
        ListNode tail = null;

        while (num != 0) {
            int val = num % 10;
            num = num / 10;

            if (result == null) {
                result = new ListNode(val);
                tail = result;
            } else {
                ListNode newNode = new ListNode(val);
                tail.next = newNode;
                tail = newNode;
            }
        }

        return result;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append(this.val);

            ListNode node = this.next;
            while (node != null) {
                result.append(node.val);
                node = node.next;
            }

            return result.reverse().toString();
        }
    }
}