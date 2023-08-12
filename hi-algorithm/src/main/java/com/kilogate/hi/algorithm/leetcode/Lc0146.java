package com.kilogate.hi.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存
 * <p>
 * https://leetcode-cn.com/problems/lru-cache
 *
 * @author kilogate
 * @create 2022/2/20 12:04
 **/
public class Lc0146 {
    public static void main(String[] args) {
        Lc0146 lc0146 = new Lc0146();
        LRUCache lruCache = lc0146.new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

    class LRUCache {
        private int capacity;
        private Map<Integer, Node> cache;
        private Node head;
        private Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap();
            head = new Node();
            tail = new Node();

            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = cache.get(key);

            if (node == null) {
                return -1;
            }

            moveToHead(node);
            return node.val;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);

            if (node != null) {
                node.val = value;
                moveToHead(node);
                return;
            }

            if (cache.size() >= capacity) {
                removeFromTail();
            }

            Node newNode = new Node();
            newNode.key = key;
            newNode.val = value;
            addToHead(newNode);

            cache.put(key, newNode);
        }

        private void addToHead(Node node) {
            Node next = head.next;

            head.next = node;
            node.next = next;
            next.prev = node;
            node.prev = head;
        }

        private void removeFromTail() {
            cache.remove(tail.prev.key);
            removeNode(tail.prev);
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }

        private void removeNode(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }

        private class Node {
            Integer key;
            Integer val;
            Node prev;
            Node next;

            @Override
            public String toString() {
                StringBuilder res = new StringBuilder();

                Node curr = this;
                while (curr != null) {
                    res.append(curr.key).append(",");
                    curr = curr.next;
                }

                return res.toString();
            }
        }
    }
}
