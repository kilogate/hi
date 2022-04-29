package com.kilogate.hi.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 比较版本号
 * <p>
 * https://leetcode-cn.com/problems/compare-version-numbers/
 *
 * @author fengquanwei
 * @create 2022/4/29 17:59
 **/
public class Lc0165 {
    public static void main(String[] args) {
        System.out.println(new Lc0165().compareVersion("001.1.0000.1", "1.1.0.1"));
    }

    public int compareVersion(String version1, String version2) {
        List<Integer> list1 = parseVersion(version1);
        List<Integer> list2 = parseVersion(version2);

        int length = Math.max(list1.size(), list2.size());
        for (int i = 0; i < length; i++) {
            int v1 = i < list1.size() ? list1.get(i) : 0;
            int v2 = i < list2.size() ? list2.get(i) : 0;

            if (v1 == v2) {
                continue;
            }

            if (v1 > v2) {
                return 1;
            } else {
                return -1;
            }
        }

        return 0;
    }

    private List<Integer> parseVersion(String version) {
        List<Integer> list = new ArrayList();

        String[] strs = version.split("\\.");
        for (String str : strs) {
            list.add(Integer.parseInt(str));
        }

        return list;
    }
}
