package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.util.ListNode;
import com.kilogate.hi.algorithm.util.TreeNode;
import com.kilogate.hi.algorithm.util.ListNodeUtil;

/**
 * 有序链表转换二叉搜索树
 * <p>
 * https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
 *
 * @author kilogate
 * @create 2022/3/24 15:25
 **/
public class Lc0109 {
    public static void main(String[] args) {
        Lc0109 lc0109 = new Lc0109();
        TreeNode treeNode = lc0109.sortedListToBST(ListNodeUtil.buildListNode("-10,-3,0,5,9"));
        System.out.println(treeNode);
    }

    public TreeNode sortedListToBST(ListNode head) {
        return buildBST(head, null);
    }

    private TreeNode buildBST(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }

        ListNode mid = findMidNode(left, right);

        TreeNode node = new TreeNode(mid.val);
        node.left = buildBST(left, mid);
        node.right = buildBST(mid.next, right);

        return node;
    }

    private ListNode findMidNode(ListNode left, ListNode right) {
        ListNode slow = left, fast = left;

        while (fast != right && fast.next != right) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
