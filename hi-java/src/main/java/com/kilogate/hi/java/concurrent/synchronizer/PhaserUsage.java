package com.kilogate.hi.java.concurrent.synchronizer;

import java.util.concurrent.Phaser;

/**
 * 阶段器的使用
 *
 * @author kilogate
 * @create 2020/7/30 上午12:05
 **/
public class PhaserUsage {
    public static void main(String[] args) {
        // 最大迭代次数
        int iterations = 6;

        // 阶段器
        Phaser phaser = new Phaser() {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println(String.format("%24s [阶段%s] 已结束, 已注册成员数: %s", Thread.currentThread(), phase, registeredParties));

                if (phase >= iterations) {
                    System.out.println(String.format("%24s 超过迭代次数，退出", Thread.currentThread()));
                    return true;
                }

                if (registeredParties == 0) {
                    System.out.println(String.format("%24s 没有已注册成员了，退出", Thread.currentThread()));
                    return true;
                }

                return false;
            }
        };

        phaser.register();

        // 任务
        for (int i = 0; i < 3; i++) {
            phaser.register();

            long now = System.currentTimeMillis();
            int taskNum = i;
            new Thread(() -> {
                while (!phaser.isTerminated()) {
                    try {
                        Thread.sleep((long) (2000 * Math.random()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (System.currentTimeMillis() - now > taskNum * 2000) {
                        System.out.println(String.format("%24s [阶段%s] [成员%s] 已完成本阶段任务, 没有下一阶段任务了, 直接退出", Thread.currentThread(), phaser.getPhase(), taskNum));
                        phaser.arriveAndDeregister();
                        return;
                    }

                    System.out.println(String.format("%24s [阶段%s] [成员%s] 已完成本阶段任务, 等待执行下一阶段的任务", Thread.currentThread(), phaser.getPhase(), taskNum));
                    phaser.arriveAndAwaitAdvance();
                }
            }).start();
        }

        phaser.arriveAndDeregister();
    }
}
