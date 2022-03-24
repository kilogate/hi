package com.kilogate.hi.algorithm.common;

/**
 * 单链表的节点
 *
 * @author fengquanwei
 * @create 2022/3/6 10:34
 **/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static void main(String[] args) {
        ListNode listNode = buildListNode("1,2,3,4,5,6");
        System.out.println(listNode);
    }

    public static ListNode buildListNode(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        String[] elements = data.split(",");
        ListNode next = null;
        for (int i = elements.length - 1; i >= 0; i--) {
            ListNode node = new ListNode(Integer.valueOf(elements[i]));
            node.next = next;
            next = node;
        }

        return next;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        ListNode node = this;
        while (node != null) {
            res.append(node.val).append(",");
            node = node.next;
        }

        return res.substring(0, res.length() - 1);
    }
}
