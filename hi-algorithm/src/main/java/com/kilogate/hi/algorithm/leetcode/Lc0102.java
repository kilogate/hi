package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.util.TreeNode;
import com.kilogate.hi.algorithm.util.TreeNodeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 *
 * @author fengquanwei
 * @create 2022/4/29 16:41
 **/
public class Lc0102 {
    public static void main(String[] args) {
        TreeNode treeNode = TreeNodeUtil.buildTreeNode("[3,9,20,null,null,15,7]");
        List<List<Integer>> res = new Lc0102().levelOrder(treeNode);
        res.stream().forEach(System.out::println);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subRes = new ArrayList();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                subRes.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(subRes);
        }

        return res;
    }
}
