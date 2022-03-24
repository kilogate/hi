package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.common.ListNode;
import com.kilogate.hi.algorithm.util.ListNodeUtil;

/**
 * 合并两个有序链表
 * <p>
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * @author fengquanwei
 * @create 2022/3/12 21:24
 **/
public class Lc0021 {
    public static void main(String[] args) {
        Lc0021 lc0021 = new Lc0021();
        ListNode res = lc0021.mergeTwoLists(ListNodeUtil.buildListNode("1,2,4"), ListNodeUtil.buildListNode("1,3,4"));
        System.out.println(res);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode preRoot = new ListNode(-1);

        ListNode pre = preRoot;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                pre.next = list1;
                pre = list1;
                list1 = list1.next;
            } else {
                pre.next = list2;
                pre = list2;
                list2 = list2.next;
            }
        }

        pre.next = list1 == null ? list2 : list1;

        return preRoot.next;
    }
}
