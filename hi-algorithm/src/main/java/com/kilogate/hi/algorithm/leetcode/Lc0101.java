package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 对称二叉树
 * <p>
 * https://leetcode-cn.com/problems/symmetric-tree/
 *
 * @author kilogate
 * @create 2022/3/5 17:16
 **/
public class Lc0101 {
    public static void main(String[] args) {
        Lc0101 lc0101 = new Lc0101();

        TreeNode left = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        TreeNode right = new TreeNode(2, new TreeNode(4), new TreeNode(3));
        TreeNode root = new TreeNode(1, left, right);

        boolean res = lc0101.isSymmetric2(root);
        System.out.println(res);
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left == null && right == null) {
                continue;
            }

            if (left == null || right == null) {
                return false;
            }

            if (left.val != right.val) {
                return false;
            }

            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }

        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}
