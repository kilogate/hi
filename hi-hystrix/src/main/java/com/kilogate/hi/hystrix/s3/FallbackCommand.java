package com.kilogate.hi.hystrix.s3;

import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * FallbackCommand
 *
 * @author kilogate
 * @create 2021/8/23 15:45
 **/
public class FallbackCommand extends HystrixCommand<String> {
    private final String name;

    public FallbackCommand(String name) {
        super(Setter
                // 设置分组名
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloFallbackGroup"))
                // 设置依赖名
                .andCommandKey(HystrixCommandKey.Factory.asKey("HelloFallbackCommand"))
                // 设置线程池名
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HelloFallbackThreadPool"))
                // 设置超时时间
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(500)));
        this.name = name;
    }

    @Override
    protected String getFallback() {
        return "【Fallback】Execute Failed";
    }

    @Override
    protected String run() throws Exception {
        TimeUnit.MILLISECONDS.sleep(1000);
        return "Hello, " + name + "! currentThread:" + Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        FallbackCommand fallbackCommand = new FallbackCommand("Lask");
        String result = fallbackCommand.execute();
        System.out.println(result);
    }
}
