package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.util.TreeNode;
import com.kilogate.hi.algorithm.util.TreeNodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的所有路径
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-paths/
 *
 * @author fengquanwei
 * @create 2022/3/12 11:12
 **/
public class Lc0257 {
    public static void main(String[] args) {
        Lc0257 lc0257 = new Lc0257();

        TreeNode root = TreeNodeUtil.buildTreeNode("[1,2,3,null,5]");
        List<String> res = lc0257.binaryTreePaths(root);
        System.out.println(res);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList();
        }

        List<String> res = new ArrayList();
        getBinaryTreePath(root, res, "");
        return res;
    }

    private void getBinaryTreePath(TreeNode node, List<String> res, String solution) {
        solution = solution + "->" + node.val;

        if (node.left == null && node.right == null) {
            res.add(solution.substring(2, solution.length()));
            return;
        }

        if (node.left != null) {
            getBinaryTreePath(node.left, res, solution);
        }

        if (node.right != null) {
            getBinaryTreePath(node.right, res, solution);
        }
    }
}
