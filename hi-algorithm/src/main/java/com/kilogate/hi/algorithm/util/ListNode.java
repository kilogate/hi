package com.kilogate.hi.algorithm.util;

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
