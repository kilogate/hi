package com.kilogate.hi.java.basic;

/**
 * 内部类的使用
 *
 * @author kilogate
 * @create 2020/8/8 下午3:55
 **/
public class InnerClassUsage {
    public static void main(String[] args) {
        System.out.println(sum(1, 3));
    }

    public static int sum(int a, int b) {
        // 利用匿名内部类，在静态方法中获取该静态方法所在的类
        Class<?> enclosingClass = new Object() {}.getClass().getEnclosingClass();

        System.out.println(enclosingClass);

        return a + b;
    }
}
