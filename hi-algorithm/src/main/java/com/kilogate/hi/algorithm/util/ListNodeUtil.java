package com.kilogate.hi.algorithm.util;

/**
 * ListNodeUtil
 *
 * @author fengquanwei
 * @create 2022/3/24 22:58
 **/
public class ListNodeUtil {
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
}
