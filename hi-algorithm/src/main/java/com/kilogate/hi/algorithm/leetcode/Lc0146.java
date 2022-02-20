package com.kilogate.hi.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存
 * <p>
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
