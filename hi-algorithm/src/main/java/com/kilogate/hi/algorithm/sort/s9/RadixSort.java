package com.kilogate.hi.algorithm.sort.s9;

/**
 * 基数排序（非比较排序）
 *
 * @author kilogate
 * @create 2021/6/14 22:42
 **/
public class RadixSort {
    /**
     * 基数排序
     *
     * @param p 头结点
     * @param r 基数
     * @param d 位数
     */
    public static Node radixSort(Node p, int r, int d) {
        if (p == null || p.next == null || r <= 0 || d <= 0) {
            return p;
        }

        // 首尾指针
        Node[] heads = new Node[r];
        Node[] tails = new Node[r];

        // 从低位到高位分配和收集
        for (int i = 0; i < d; i++) {
            // 初始化首尾指针
            for (int j = 0; j < r; j++) {
                heads[j] = null;
                tails[j] = null;
            }

            // 分配
            while (p != null) {
                // 找第 k 个链队
                int k = p.data[i];

                if (heads[k] == null) {
                    heads[k] = p;
                    tails[k] = p;
                } else {
                    tails[k].next = p;
                    tails[k] = p;
                }

                p = p.next;
            }

            // 收集
            p = null;
            Node t = null;

            for (int j = 0; j < r; j++) {
                if (heads[j] == null) {
                    continue;
                }

                if (p == null) {
                    p = heads[j];
                    t = tails[j];
                } else {
                    t.next = heads[j];
                    t = tails[j];
                }
            }

            t.next = null;
        }

        return p;
    }

    /**
     * 结点
     */
    private static class Node {
        int[] data;
        Node next;

        public Node(int[] data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Node node = buildNode(new int[]{75, 23, 98, 44, 57, 12, 29, 64, 38, 82}, 10, 2);

        node = radixSort(node, 10, 2);

        printNode(node);
    }

    private static Node buildNode(int[] elements, int r, int d) {
        if (elements == null || r <= 0 || d <= 0) {
            return null;
        }

        // 从后往前构建
        Node node = null;
        Node pre = null;
        for (int i = elements.length - 1; i >= 0; i--) {
            int[] data = new int[d];

            int number = elements[i];
            for (int j = 0; j < d; j++) {
                data[j] = number % r;
                number = number / r;
            }

            node = new Node(data, pre);
            pre = node;
        }

        return node;
    }

    private static void printNode(Node node) {
        if (node == null) {
            System.out.println(node);
        }

        StringBuffer result = new StringBuffer();

        while (node != null) {
            int[] data = node.data;

            for (int i = data.length - 1; i >= 0; i--) {
                result.append(data[i]);
            }

            result.append(", ");

            node = node.next;
        }

        System.out.println(result.subSequence(0, result.length() - 2));
    }
}

