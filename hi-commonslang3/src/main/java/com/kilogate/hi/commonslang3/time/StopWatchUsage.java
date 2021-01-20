package com.kilogate.hi.commonslang3.time;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * StopWatchUsage
 *
 * @author kilogate
 * @create 2020/11/12 17:00
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

        System.out.println(stopWatch.getTime(TimeUnit.SECONDS));
        System.out.println(stopWatch.getTime(TimeUnit.MILLISECONDS));
        System.out.println(stopWatch.getTime(TimeUnit.MICROSECONDS));
        System.out.println(stopWatch.getTime(TimeUnit.NANOSECONDS));

        // task 2

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(stopWatch.getTime(TimeUnit.SECONDS));
        System.out.println(stopWatch.getTime(TimeUnit.MILLISECONDS));
        System.out.println(stopWatch.getTime(TimeUnit.MICROSECONDS));
        System.out.println(stopWatch.getTime(TimeUnit.NANOSECONDS));

        stopWatch.stop();
    }
}
