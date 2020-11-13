package com.kilogate.hi.guava.time;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * StopWatchUsage
 *
 * @author kilogate
 * @create 2020/11/13 14:50
 **/
public class StopWatchUsage {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
        System.out.println(stopwatch.elapsed(TimeUnit.MICROSECONDS));
        System.out.println(stopwatch.elapsed(TimeUnit.NANOSECONDS));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
        System.out.println(stopwatch.elapsed(TimeUnit.MICROSECONDS));
        System.out.println(stopwatch.elapsed(TimeUnit.NANOSECONDS));

        stopwatch.stop();
    }
}
