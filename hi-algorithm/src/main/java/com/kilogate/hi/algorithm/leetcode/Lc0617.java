package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.util.TreeNode;

/**
 * 合并二叉树
 * <p>
 * https://leetcode-cn.com/problems/merge-two-binary-trees
 *
 * @author fengquanwei
 * @create 2022/3/6 10:23
 **/
public class Lc0617 {
    public static void main(String[] args) {
        Lc0617 lc0617 = new Lc0617();

        TreeNode left1 = new TreeNode(3, new TreeNode(5), null);
        TreeNode right1 = new TreeNode(2);
        TreeNode root1 = new TreeNode(1, left1, right1);

        TreeNode left2 = new TreeNode(1, null, new TreeNode(4));
        TreeNode right2 = new TreeNode(3, null, new TreeNode(7));
        TreeNode root2 = new TreeNode(2, left2, right2);

        TreeNode res = lc0617.mergeTrees(root1, root2);
        System.out.println(res);
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }

        if (root2 == null) {
            return root1;
        }

        TreeNode res = new TreeNode(root1.val + root2.val);
        res.left = mergeTrees(root1.left, root2.left);
        res.right = mergeTrees(root1.right, root2.right);
        return res;
    }
}
