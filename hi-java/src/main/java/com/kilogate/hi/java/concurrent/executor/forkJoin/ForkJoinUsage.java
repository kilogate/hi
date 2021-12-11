package com.kilogate.hi.java.concurrent.executor.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * ForkJoin 的用法
 *
 * @author kilogate
 * @create 2020/8/1 下午4:10
 **/
public class ForkJoinUsage {
    public static void main(String[] args) {
        final int SIZE = 10000000;
        double[] numbers = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = Math.random();
        }

        Counter counter = new Counter(numbers, 0, numbers.length, x -> x > 0.5);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(counter);
        System.out.println(counter.join());
    }

    static class Counter extends RecursiveTask<Integer> {
        public static final int THRESHOLD = 1000;
        private double[] values;
        private int from;
        private int to;
        private DoublePredicate filter;

        public Counter(double[] values, int from, int to, DoublePredicate filter) {
            this.values = values;
            this.from = from;
            this.to = to;
            this.filter = filter;
        }

        @Override
        protected Integer compute() {
            if (to - from < THRESHOLD) {
                int count = 0;
                for (int i = from; i < to; i++) {
                    if (filter.test(values[i])) {
                        count++;
                    }
                }
                return count;
            } else {
                int mid = (from + to) / 2;
                Counter first = new Counter(values, from, mid, filter);
                Counter second = new Counter(values, mid, to, filter);
                invokeAll(first, second);
                return first.join() + second.join();
            }
        }
    }
}
