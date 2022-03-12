package com.kilogate.hi.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存
 * <p>
 * https://leetcode-cn.com/problems/lru-cache
 *
 * @author fengquanwei
 * @create 2022/2/20 12:04
 **/
public class Lc0146 {
    public static void main(String[] args) {
        Lc0146 lc0146 = new Lc0146();
        LRUCache lruCache = lc0146.new LRUCache(2);
        lruCache.put(1, 0);
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
        private Map<Integer, Node> cache;
        private int capacity;
        private Node headNode;
        private Node tailNode;

        public LRUCache(int capacity) {
            cache = new HashMap();
            this.capacity = capacity;
            headNode = new Node();
            tailNode = new Node();

            headNode.next = tailNode;
            tailNode.prev = headNode;
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

            Node newNode = new Node();
            newNode.key = key;
            newNode.val = value;
            addToHead(newNode);

            cache.put(key, newNode);
            if (cache.size() > capacity) {
                removeFromTail();
            }
        }

        private void removeFromTail() {
            // remove from list
            Node currNode = tailNode.prev;
            Node prevNode = currNode.prev;
            prevNode.next = tailNode;
            tailNode.prev = prevNode;

            // remove from cache
            cache.remove(currNode.key);
        }

        private void addToHead(Node newNode) {
            Node secondNode = headNode.next;
            headNode.next = newNode;
            newNode.next = secondNode;
            secondNode.prev = newNode;
            newNode.prev = headNode;
        }

        private void moveToHead(Node currNode) {
            // delete currNode
            Node prevNode = currNode.prev;
            Node nextNode = currNode.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            // add currNode to head
            addToHead(currNode);
        }

        private class Node {
            Integer key;
            Integer val;
            Node prev;
            Node next;

            @Override
            public String toString() {
                StringBuilder res = new StringBuilder();

                Node currNode = this;
                while (currNode != null) {
                    res.append(currNode.key).append(",");
                    currNode = currNode.next;
                }

                return res.toString();
            }
        }
    }
}
