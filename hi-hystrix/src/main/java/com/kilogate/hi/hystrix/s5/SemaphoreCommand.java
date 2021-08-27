package com.kilogate.hi.hystrix.s5;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesCommandDefault;

/**
 * SemaphoreCommand
 *
 * @author kilogate
 * @create 2021/8/23 15:15
 **/
public class SemaphoreCommand extends HystrixCommand<String> {
    private final String name;

    public SemaphoreCommand(String name) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("SemaphoreGroup"))
                // 配置信号量隔离方式，默认采用线程池隔离
                .andCommandPropertiesDefaults(HystrixPropertiesCommandDefault.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE))
        );
        this.name = name;
    }

    @Override
    protected String run() {
        // 依赖逻辑
        return "Hello, " + name + "! currentThread:" + Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        SemaphoreCommand helloCommand = new SemaphoreCommand("Sync");
        String result = helloCommand.execute();
        System.out.println(result);
    }
}
