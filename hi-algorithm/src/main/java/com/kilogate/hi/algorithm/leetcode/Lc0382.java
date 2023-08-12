package com.kilogate.hi.algorithm.leetcode;

import com.kilogate.hi.algorithm.util.ListNode;
import com.kilogate.hi.algorithm.util.ListNodeUtil;

import java.util.Random;

/**
 * 链表随机节点
 * <p>
 * https://leetcode-cn.com/problems/linked-list-random-node/
 *
 * @author kilogate
 * @create 2022/4/29 15:53
 **/
public class Lc0382 {
    private ListNode head;

    public static void main(String[] args) {
        Lc0382 lc0382 = new Lc0382(ListNodeUtil.buildListNode("1,2,3"));
        for (int i = 0; i < 100; i++) {
            System.out.println(lc0382.getRandom());
        }
    }

    private Lc0382(ListNode head) {
        this.head = head;
    }

    /**
     * 水塘抽样
     *
     * @return
     */
    private int getRandom() {
        ListNode ans = this.head;
        ListNode curr = this.head.next;
        int i = 2;
        while (curr != null) {
            if (new Random().nextInt(i) == 0) {
                ans = curr;
            }
            i++;
            curr = curr.next;
        }
        return ans.val;
    }
}
