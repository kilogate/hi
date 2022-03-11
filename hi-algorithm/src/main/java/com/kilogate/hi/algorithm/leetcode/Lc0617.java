package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.common.TreeNode;

/**
 * 合并二叉树
 * <p>
 * 给你两棵二叉树： root1 和 root2 。
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 * 返回合并后的二叉树。
 * 注意: 合并过程必须从两个树的根节点开始。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
