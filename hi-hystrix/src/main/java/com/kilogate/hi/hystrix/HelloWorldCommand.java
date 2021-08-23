package com.kilogate.hi.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

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
        subscribe();
    }

    private static void helloWorld() throws ExecutionException, InterruptedException, TimeoutException {
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

    private static void subscribe() {
        // 注册观察者事件拦截
        Observable<String> observe = new HelloWorldCommand("Lask").observe();

        // 注册结果回调事件
        observe.subscribe(new Action1<String>() {
            @Override
            public void call(String result) {
                // 处理执行结果
                System.out.println(result);
            }
        });

        // 注册完整执行生命周期事件
        observe.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                // 在 onError/onNext 完成后回调
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                // 在异常时回调
                System.out.println("onError: " + throwable.getMessage());
                throwable.printStackTrace();
            }

            @Override
            public void onNext(String result) {
                // 在获取结果时回调
                System.out.println("onNext: " + result);
            }
        });
    }
}
