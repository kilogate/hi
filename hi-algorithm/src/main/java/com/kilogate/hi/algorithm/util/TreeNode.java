package com.kilogate.hi.algorithm.util;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的节点
 *
 * @author kilogate
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

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);

        int nullCount = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                res.append("null,");
                nullCount++;
                continue;
            } else {
                nullCount = 0;
            }

            res.append(node.val).append(",");
            queue.offer(node.left);
            queue.offer(node.right);
        }

        return "[" + res.subSequence(0, res.length() - 1 - nullCount * 5).toString() + "]";
    }
}
