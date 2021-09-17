package com.kilogate.hi.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LruMap
 *
 * @author kilogate
 * @create 2021/9/17 19:26
 **/
public class LruMap<K, V> {
    private LinkedHashMap<K, V> map;

    public LruMap(int size) {
        float loadFactor = 0.75F;
        int capacity = (int) Math.ceil(size / loadFactor) + 1;

        this.map = new LinkedHashMap<K, V>(capacity, loadFactor, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > size;
            }
        };
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized void clear() {
        map.clear();
    }

    public static void main(String[] args) {
        LruMap<Integer, String> lruMap = new LruMap(5);

        for (int i = 0; i < 10; i++) {
            lruMap.put(i, i + "");
        }

        for (int i = 0; i < 10; i++) {
            String v = lruMap.get(i);
            System.out.println(v);
        }
    }
}
