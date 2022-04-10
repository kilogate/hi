package com.kilogate.hi.algorithm.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 多叉树的节点
 *
 * @author fengquanwei
 * @create 2022/4/10 17:46
 **/
public class MultiTreeNode {
    public int val;
    public List<MultiTreeNode> children;

    public MultiTreeNode() {
    }

    public MultiTreeNode(int _val) {
        val = _val;
    }

    public MultiTreeNode(int _val, List<MultiTreeNode> _children) {
        val = _val;
        children = _children;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Queue<MultiTreeNode> queue = new LinkedList<>();
        queue.offer(this);
        queue.offer(null);
        while (!queue.isEmpty()) {
            MultiTreeNode node = queue.poll();
            if (node == null) {
                sb.append(",").append("null");
                continue;
            }

            sb.append(",").append(node.val);
            if (node.children != null) {
                for (MultiTreeNode ch : node.children) {
                    queue.offer(ch);
                }
                queue.offer(null);
            }
        }

        String res = sb.substring(1);
        res = res.replaceAll("(,null)*$", "");
        return "[" + res + "]";
    }
}
