package com.kilogate.hi.algorithm.util;

import java.util.*;

/**
 * MultiTreeNodeUtil
 *
 * @author fengquanwei
 * @create 2022/4/10 17:48
 **/
public class MultiTreeNodeUtil {
    public static MultiTreeNode buildMultiTreeNode(String str) {
        if (str == null || str.length() <= 2) {
            return null;
        }

        str = str.substring(1, str.length() - 1);
        String[] elements = str.split(",");
        MultiTreeNode root = new MultiTreeNode(Integer.valueOf(elements[0]));

        if (elements.length <= 2) {
            return root;
        }

        Queue<MultiTreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<MultiTreeNode> children = new ArrayList<>();
        for (int i = 2; i < elements.length; i++) {
            if (Objects.equals(elements[i], "null")) {
                MultiTreeNode parent = queue.poll();
                parent.children = children;
                children = new ArrayList<>();
                continue;
            }

            MultiTreeNode node = new MultiTreeNode(Integer.valueOf(elements[i]));
            children.add(node);
            queue.offer(node);
        }

        MultiTreeNode parent = queue.poll();
        parent.children = children;

        return root;
    }
}
