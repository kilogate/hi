package com.kilogate.hi.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * HelloWorldCommand
 *
 * @author kilogate
 * @create 2021/8/23 15:15
 **/
public class HelloWorldCommand extends HystrixCommand<String> {
    private final String name;

    public HelloWorldCommand(String name) {
        // 最少配置：指定命令组名（CommandGroup）
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        // 依赖逻辑
        return "Hello, " + name + "! currentThread:" + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 每个 Command 对象只能调用一次
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("Sync");

        // 同步调用，等价于：helloWorldCommand.queue().get()
        String result = helloWorldCommand.execute();
        System.out.println(result);

        // 异步调用
        helloWorldCommand = new HelloWorldCommand("Async");
        Future<String> future = helloWorldCommand.queue();
        result = future.get(1000, TimeUnit.MILLISECONDS);
        System.out.println(result);
    }
}
