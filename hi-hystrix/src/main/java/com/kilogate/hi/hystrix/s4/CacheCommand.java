package com.kilogate.hi.hystrix.s4;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * CacheCommand
 *
 * @author kilogate
 * @create 2021/8/23 15:15
 **/
public class CacheCommand extends HystrixCommand<String> {
    private final String name;

    public CacheCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("CacheGroup"));
        this.name = name;
    }

    @Override
    protected String getCacheKey() {
        return "CacheKey_" + this.name;
    }

    @Override
    protected String run() {
        // 依赖逻辑
        return "Hello, " + name + "! currentThread:" + Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        // 初始化上下文
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        CacheCommand cacheCommand1 = new CacheCommand("Lask");
        String result1 = cacheCommand1.execute();
        boolean responseFromCache1 = cacheCommand1.isResponseFromCache();

        CacheCommand cacheCommand2 = new CacheCommand("Lask");
        String result2 = cacheCommand2.execute();
        boolean responseFromCache2 = cacheCommand2.isResponseFromCache();

        context.shutdown();

        // 初始化上下文
        context = HystrixRequestContext.initializeContext();

        CacheCommand cacheCommand3 = new CacheCommand("Lask");
        String result3 = cacheCommand3.execute();
        boolean responseFromCache3 = cacheCommand3.isResponseFromCache();

        context.shutdown();

        System.out.println();
    }
}
