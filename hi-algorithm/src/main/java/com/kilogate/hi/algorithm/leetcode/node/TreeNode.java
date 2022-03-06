package com.kilogate.hi.algorithm.leetcode.node;

/**
 * 二叉树的节点
 *
 * @author fengquanwei
 * @create 2022/3/6 10:24
 **/
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
