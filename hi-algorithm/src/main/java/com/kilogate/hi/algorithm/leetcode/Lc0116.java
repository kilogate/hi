package com.kilogate.hi.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针
 * <p>
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 *
 * @author fengquanwei
 * @create 2022/3/6 11:11
 **/
public class Lc0116 {
    public static void main(String[] args) {
        Lc0116 lc0116 = new Lc0116();

        Node left = new Node(2, new Node(4), new Node(5), null);
        Node right = new Node(3, new Node(6), new Node(7), null);
        Node root = new Node(1, left, right, null);

        Node res = lc0116.connect2(root);
        System.out.println(res);
    }

    public Node connect2(Node root) {
        if (root == null) {
            return root;
        }

        Node head = root;
        while (head.left != null) {
            Node curr = head;
            while (curr != null) {
                curr.left.next = curr.right;

                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }

                curr = curr.next;
            }

            head = head.left;
        }

        return root;
    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> queue = new LinkedList();
        queue.offer(root);

        Node prev = null, curr = null;
        int row = 1, index = 1;
        while (!queue.isEmpty()) {
            curr = queue.poll();

            if (index == Math.pow(2, row)) {
                row++;
            } else if (prev != null) {
                prev.next = curr;
            }

            prev = curr;
            index++;

            if (curr.left != null) {
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
            }
        }

        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
