package com.kilogate.hi.algorithm.util;

import java.util.Objects;

/**
 * TreeNodeUtil
 *
 * @author kilogate
 * @create 2022/3/24 23:00
 **/
public class TreeNodeUtil {
    public static void main(String[] args) {
        TreeNode treeNode = buildTreeNode("[3,5,1,6,2,0,8,null,null,7,4]");
        System.out.println(treeNode);
    }

    public static TreeNode buildTreeNode(String data) {
        if (data == null || data.length() <= 2) {
            return null;
        }

        String[] elements = data.substring(1, data.length() - 1).split(",");

        TreeNode[] nodes = new TreeNode[elements.length];
        for (int i = 0; i < elements.length; i++) {
            if (Objects.equals(elements[i], "null")) {
                nodes[i] = null;
                continue;
            }

            nodes[i] = new TreeNode();
        }

        for (int i = 0; i < elements.length; i++) {
            if (!Objects.equals(elements[i], "null")) {
                nodes[i].val = Integer.valueOf(elements[i]);
            }

            int l = 2 * i + 1;
            if (l < elements.length) {
                nodes[i].left = nodes[l];
            }

            int r = 2 * i + 2;
            if (r < elements.length) {
                nodes[i].right = nodes[r];
            }
        }

        return nodes[0];
    }
}
