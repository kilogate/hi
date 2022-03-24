package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.common.TreeNode;
import com.kilogate.hi.algorithm.util.TreeNodeUtil;

/**
 * 二叉树的最近公共祖先
 * <p>
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 *
 * @author fengquanwei
 * @create 2022/3/10 23:28
 **/
public class Lc0236 {
    public static void main(String[] args) {
        Lc0236 lc0236 = new Lc0236();

        TreeNode root = TreeNodeUtil.buildTreeNode("[3,5,1,6,2,0,8,null,null,7,4]");
        TreeNode res = lc0236.lowestCommonAncestor(root, root.left.left, root.right.right);
        System.out.println(res);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode matchLeft = lowestCommonAncestor(root.left, p, q);

        if (matchLeft == null) {
            return lowestCommonAncestor(root.right, p, q);
        }

        TreeNode matchRight = lowestCommonAncestor(root.right, p, q);

        if (matchRight != null) {
            return root;
        }

        return matchLeft;
    }
}
