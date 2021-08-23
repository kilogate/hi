package com.kilogate.hi.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.concurrent.TimeUnit;

/**
 * HelloFallbackCommand
 *
 * @author kilogate
 * @create 2021/8/23 15:45
 **/
public class HelloFallbackCommand extends HystrixCommand<String> {
    private final String name;

    public HelloFallbackCommand(String name) {
        super(Setter
                // 设置命令分组
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                // 设置超时时间
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(500)));
        this.name = name;
    }

    @Override
    protected String getFallback() {
        return "Execute Failed";
    }

    @Override
    protected String run() throws Exception {
        TimeUnit.MILLISECONDS.sleep(1000);
        return "Hello, " + name + "! currentThread:" + Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        HelloFallbackCommand helloFallbackCommand = new HelloFallbackCommand("Lask");
        String result = helloFallbackCommand.execute();
        System.out.println(result);
    }
}
