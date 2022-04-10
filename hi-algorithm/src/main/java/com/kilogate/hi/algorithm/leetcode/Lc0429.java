package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.util.MultiTreeNode;
import com.kilogate.hi.algorithm.util.MultiTreeNodeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * N 叉树的层序遍历
 * <p>
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 *
 * @author fengquanwei
 * @create 2022/4/10 17:00
 **/
public class Lc0429 {
    public static void main(String[] args) {
        Lc0429 lc0429 = new Lc0429();
        MultiTreeNode root = MultiTreeNodeUtil.buildMultiTreeNode("[1,null,3,2,4,null,5,6]");
        MultiTreeNode root2 = MultiTreeNodeUtil.buildMultiTreeNode("[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]");
        List<List<Integer>> res = lc0429.levelOrder(root);
        System.out.println(res);
    }

    public List<List<Integer>> levelOrder(MultiTreeNode root) {
        List<List<Integer>> res = new ArrayList();

        if (root == null) {
            return res;
        }

        Queue<MultiTreeNode> queue = new LinkedList();
        queue.offer(root);
        queue.offer(null);
        int rest = 1;
        List<Integer> layer = new ArrayList();
        while (!queue.isEmpty()) {
            MultiTreeNode node = queue.poll();
            if (node == null) {
                res.add(layer);
                layer = new ArrayList();

                if (rest > 0) {
                    queue.offer(null);
                }
                continue;
            }

            layer.add(node.val);
            rest--;

            if (node.children != null) {
                for (MultiTreeNode ch : node.children) {
                    queue.offer(ch);
                    rest++;
                }
            }
        }

        return res;
    }
}
