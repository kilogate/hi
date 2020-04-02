package com.kilogate.hi.java.init;

/**
 * S
 *
 * @author kilogate
 * @create 2020/4/2 下午11:02
 **/
public class S extends P {
    // ==================== static start ====================

    private static int nextSId = getNextSId();

    private static int getNextSId() {
        System.out.println("子类静态变量");
        return 0;
    }

    static {
        System.out.println("子类静态代码块");
    }

    // ==================== static end ====================

    // ==================== non static start ====================

    private int sid = getSid();


    public int getSid() {
        System.out.println("子类非静态变量");
        return 1;
    }

    {
        System.out.println("子类非静态代码块");
    }

    // ==================== non static end ====================

    public S() {
        System.out.println("子类构造器");
    }

}
