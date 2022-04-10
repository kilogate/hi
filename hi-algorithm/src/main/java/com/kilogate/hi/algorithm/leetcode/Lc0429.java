package com.kilogate.hi.algorithm.leetcode;

import java.util.*;

/**
 * N 叉树的层序遍历
 * <p>
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 *
 * @author fengquanwei
 * @create 2022/4/10 17:00
 **/
public class Lc0429 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            Queue<Node> queue = new LinkedList<>();
            queue.offer(this);
            queue.offer(null);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node == null) {
                    sb.append(",").append("null");
                    continue;
                }

                sb.append(",").append(node.val);
                if (node.children != null) {
                    for (Node ch : node.children) {
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

    public static void main(String[] args) {
        Lc0429 lc0429 = new Lc0429();
        Node root = lc0429.buildNode("[1,null,3,2,4,null,5,6]");
        Node root2 = lc0429.buildNode("[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]");
        List<List<Integer>> res = lc0429.levelOrder(root);
        System.out.println(res);
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList();

        if (root == null) {
            return res;
        }

        Queue<Node> queue = new LinkedList();
        queue.offer(root);
        queue.offer(null);
        int rest = 1;
        List<Integer> layer = new ArrayList();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
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
                for (Node ch : node.children) {
                    queue.offer(ch);
                    rest++;
                }
            }
        }

        return res;
    }

    private Node buildNode(String str) {
        if (str == null || str.length() <= 2) {
            return null;
        }

        str = str.substring(1, str.length() - 1);
        String[] elements = str.split(",");
        Node root = new Node(Integer.valueOf(elements[0]));

        if (elements.length <= 2) {
            return root;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        List<Node> children = new ArrayList<>();
        for (int i = 2; i < elements.length; i++) {
            if (Objects.equals(elements[i], "null")) {
                Node parent = queue.poll();
                parent.children = children;
                children = new ArrayList<>();
                continue;
            }

            Node node = new Node(Integer.valueOf(elements[i]));
            children.add(node);
            queue.offer(node);
        }

        Node parent = queue.poll();
        parent.children = children;

        return root;
    }
}
