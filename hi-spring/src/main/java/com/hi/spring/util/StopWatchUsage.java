package com.hi.spring.util;

import org.springframework.util.StopWatch;

/**
 * StopWatchUsage
 *
 * @author kilogate
 * @create 2020/11/12 16:04
 **/
public class StopWatchUsage {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();

        // task 1

        stopWatch.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds());
        System.out.println(stopWatch.getTotalTimeMillis());

        // task 2

        stopWatch.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeSeconds());
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
