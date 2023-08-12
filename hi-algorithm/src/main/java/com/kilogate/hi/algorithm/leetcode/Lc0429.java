package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.util.Node;
import com.kilogate.hi.algorithm.util.NodeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * N 叉树的层序遍历
 * <p>
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 *
 * @author kilogate
 * @create 2022/4/10 17:00
 **/
public class Lc0429 {
    public static void main(String[] args) {
        Lc0429 lc0429 = new Lc0429();
        Node root = NodeUtil.buildNode("[1,null,3,2,4,null,5,6]");
        List<List<Integer>> res = lc0429.levelOrder(root);
        System.out.println(res);
    }

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList();
        Queue<Node> queue = new LinkedList();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> level = new ArrayList();

            for (int i = 0; i < count; i++) {
                Node node = queue.poll();
                level.add(node.val);
                if (node.children != null) {
                    for (Node child : node.children) {
                        queue.offer(child);
                    }
                }
            }

            res.add(level);
        }

        return res;
    }
}
