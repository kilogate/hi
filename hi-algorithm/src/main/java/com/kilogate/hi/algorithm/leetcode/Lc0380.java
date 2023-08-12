package com.kilogate.hi.algorithm.leetcode;

import java.util.*;

/**
 * O(1) 时间插入、删除和获取随机元素
 * <p>
 * https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 *
 * @author kilogate
 * @create 2022/4/13 23:24
 **/
public class Lc0380 {
    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.insert(3));
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.remove(1));
        System.out.println(randomizedSet.remove(3));
        System.out.println(randomizedSet.remove(4));
    }

    private static class RandomizedSet {
        private List<Integer> list;
        private Map<Integer, Integer> map;
        private Random random;

        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }

            int index = list.size();
            map.put(val, index);
            list.add(val);

            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }

            Integer index = map.get(val);
            Integer last = list.get(list.size() - 1);

            map.put(last, index);
            list.set(index, last);

            map.remove(val);
            list.remove(list.size() - 1);

            return true;
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }
}
