package com.kilogate.hi.hystrix.s1;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * HelloCommand
 *
 * @author kilogate
 * @create 2021/8/23 15:15
 **/
public class HelloCommand extends HystrixCommand<String> {
    private final String name;

    public HelloCommand(String name) {
        // 最少配置：指定组名（CommandGroup）
        super(HystrixCommandGroupKey.Factory.asKey("HelloGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        // 依赖逻辑
        return "Hello, " + name + "! currentThread:" + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 每个 Command 对象只能调用一次
        HelloCommand helloCommand = new HelloCommand("Sync");

        // 同步调用，等价于：helloCommand.queue().get()
        String result = helloCommand.execute();
        System.out.println(result);

        // 异步调用
        helloCommand = new HelloCommand("Async");
        Future<String> future = helloCommand.queue();
        result = future.get(1000, TimeUnit.MILLISECONDS);
        System.out.println(result);
    }
}
