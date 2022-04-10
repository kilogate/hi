package com.kilogate.hi.algorithm.util;

import java.util.*;

/**
 * NodeUtil
 *
 * @author fengquanwei
 * @create 2022/4/10 17:48
 **/
public class NodeUtil {
    public static Node buildNode(String str) {
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
