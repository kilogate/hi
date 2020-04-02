package com.kilogate.hi.java.init;

/**
 * P
 *
 * @author kilogate
 * @create 2020/4/2 下午11:02
 **/
public class P {
    // ==================== static start ====================

    private static int nextPId = getNextPId();

    private static int getNextPId() {
        System.out.println("父类静态变量");
        return 0;
    }

    static {
        System.out.println("父类静态代码块");
    }

    // ==================== static end ====================

    // ==================== non static start ====================

    private int pid = getPid();

    public int getPid() {
        System.out.println("父类非静态变量");
        return 1;
    }

    {
        System.out.println("父类非静态代码块");
    }

    // ==================== non static end ====================

    public P() {
        System.out.println("父类构造器");
    }

}
