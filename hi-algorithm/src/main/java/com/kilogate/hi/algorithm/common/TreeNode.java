package com.kilogate.hi.algorithm.common;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

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

    public static void main(String[] args) {
        TreeNode treeNode = buildTreeNode("[3,5,1,6,2,0,8,null,null,7,4]");
        System.out.println(treeNode);
    }

    public static TreeNode buildTreeNode(String data) {
        if (data == null || data.length() <= 2) {
            return null;
        }

        String[] elements = data.substring(1, data.length() - 1).split(",");

        TreeNode[] nodes = new TreeNode[elements.length];
        for (int i = 0; i < elements.length; i++) {
            if (Objects.equals(elements[i], "null")) {
                nodes[i] = null;
                continue;
            }

            nodes[i] = new TreeNode();
        }

        for (int i = 0; i < elements.length; i++) {
            if (!Objects.equals(elements[i], "null")) {
                nodes[i].val = Integer.valueOf(elements[i]);
            }

            int l = 2 * i + 1;
            if (l < elements.length) {
                nodes[i].left = nodes[l];
            }

            int r = 2 * i + 2;
            if (r < elements.length) {
                nodes[i].right = nodes[r];
            }
        }

        return nodes[0];
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
