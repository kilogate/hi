package com.kilogate.hi.java.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * 代理的使用
 *
 * @author kilogate
 * @create 2020/8/8 下午4:05
 **/
public class ProxyUsage {
    public static void main(String[] args) {
        Collection proxy = (Collection) getProxy(new ArrayList(), new AdviceImpl());

        proxy.add("abc");
        proxy.addAll(Arrays.asList("a", "b", "c", "d", "e"));
        proxy.size();
    }

    public static Object getProxy(Object target, Advice advice) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            advice.before(method, args);
            Object result = method.invoke(target, args);
            advice.after(method, args, result);

            return result;
        });
    }

    interface Advice {
        void before(Method method, Object[] args);

        void after(Method method, Object[] args, Object result);
    }

    static class AdviceImpl implements Advice {
        @Override
        public void before(Method method, Object[] args) {
            System.out.println(String.format("%s %s - %s start, args: %s", new Date(), Thread.currentThread(), method.getName(), Arrays.deepToString(args)));
        }

        @Override
        public void after(Method method, Object[] args, Object result) {
            System.out.println(String.format("%s %s - %s start, args: %s, result: %s", new Date(), Thread.currentThread(), method.getName(), Arrays.deepToString(args), result));
        }
    }

}
