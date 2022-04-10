package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.util.TreeNode;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 二叉树的序列化与反序列化
 * <p>
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 *
 * @author fengquanwei
 * @create 2022/3/11 10:48
 **/
public class Lc0297 {
    public static void main(String[] args) {
        Lc0297 lc0297 = new Lc0297();
        TreeNode node = lc0297.deserialize("1,2,null,null,3,4,null,null,5,null,null");
        String str = lc0297.serialize(node);
        System.out.println(str);
    }

    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();

        doSerialize(root, res);

        return res.toString();
    }

    private void doSerialize(TreeNode root, StringBuilder res) {
        if (root == null) {
            res.append("null,");
            return;
        }

        res.append(root.val).append(",");
        doSerialize(root.left, res);
        doSerialize(root.right, res);
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        for (String str : data.split(",")) {
            queue.offer(str);
        }

        return doDeserialize(queue);
    }

    private TreeNode doDeserialize(Queue<String> queue) {
        String str = queue.poll();
        if (Objects.equals(str, "null")) {
            return null;
        }

        TreeNode node = new TreeNode();
        node.val = Integer.valueOf(str);
        node.left = doDeserialize(queue);
        node.right = doDeserialize(queue);

        return node;
    }

}
