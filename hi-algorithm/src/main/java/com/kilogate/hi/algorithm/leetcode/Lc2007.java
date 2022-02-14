package com.kilogate.hi.algorithm.leetcode;

import java.util.*;

/**
 * 从双倍数组中还原原数组
 * <p>
 * 一个整数数组 original 可以转变成一个 双倍 数组 changed ，转变方式为将 original 中每个元素 值乘以 2 加入数组中，然后将所有元素 随机打乱 。
 * <p>
 * 给你一个数组 changed ，如果 change 是 双倍 数组，那么请你返回 original数组，否则请返回空数组。original 的元素可以以 任意 顺序返回。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-original-array-from-doubled-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author fengquanwei
 * @create 2022/2/14 11:53
 **/
public class Lc2007 {
    public static void main(String[] args) {
        Lc2007 lc2007 = new Lc2007();
        int[] originalArray = lc2007.findOriginalArray(new int[]{0, 0, 0, 0});
        System.out.println(Arrays.toString(originalArray));
    }

    public int[] findOriginalArray(int[] changed) {
        if (changed == null || changed.length == 0) {
            return new int[0];
        }

        if (changed.length % 2 == 1) {
            return new int[0];
        }

        // 构造 countMap
        Map<Integer, Integer> countMap = new TreeMap<>();
        for (int num : changed) {
            countMap.merge(num, 1, Integer::sum);
        }

        // 结果
        int[] result = new int[changed.length / 2];
        int index = 0;

        // 遍历
        Set<Integer> skipSet = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            Integer key = entry.getKey();
            Integer count = entry.getValue();

            if (skipSet.contains(key)) {
                continue;
            }

            // 特殊处理零
            if (key == 0) {
                if (count % 2 == 1) {
                    return new int[0];
                }

                for (int i = 0; i < count / 2; i++) {
                    result[index++] = key;
                }

                skipSet.add(key);
                continue;
            }

            // 找双倍元素
            int sumKey = key * 2;
            Integer sumCount = countMap.get(sumKey);
            if (sumCount == null || count > sumCount) {
                return new int[0];
            }

            for (int i = 0; i < count; i++) {
                result[index++] = key;
            }

            skipSet.add(key);

            if (count == sumCount) {
                skipSet.add(sumKey);
            } else {
                countMap.put(sumKey, sumCount - count);
            }
        }

        return result;
    }
}
