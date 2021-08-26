package com.kilogate.hi.hystrix.s2;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * SubscribeCommand
 *
 * @author kilogate
 * @create 2021/8/23 15:15
 **/
public class SubscribeCommand extends HystrixCommand<String> {
    private final String name;

    public SubscribeCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("SubscribeGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        // 依赖逻辑
        return "Hello, " + name + "! currentThread:" + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 注册观察者事件拦截
        Observable<String> observe = new SubscribeCommand("Lask").observe();

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
