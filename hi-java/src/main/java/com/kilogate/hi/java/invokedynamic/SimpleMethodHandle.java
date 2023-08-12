package com.kilogate.hi.java.invokedynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * SimpleMethodHandle
 *
 * @author kilogate
 * @create 2022/3/26 17:21
 **/
public class SimpleMethodHandle {
    static class MyPrintln {
        protected void println(String str) {
            System.out.println("[MyPrintln] " + str);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = (System.currentTimeMillis() & 1L) == 0L ? System.out : new MyPrintln();
        System.out.println(obj.getClass().toString());
        MethodHandle methodHandle = getPrintlnMethodHandle(obj);
        methodHandle.invokeExact("hello world");
    }

    private static MethodHandle getPrintlnMethodHandle(Object receiver) throws NoSuchMethodException, IllegalAccessException {
        MethodType methodType = MethodType.methodType(void.class, String.class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandle = lookup.findVirtual(receiver.getClass(), "println", methodType);
        methodHandle = methodHandle.bindTo(receiver);
        return methodHandle;
    }
}
