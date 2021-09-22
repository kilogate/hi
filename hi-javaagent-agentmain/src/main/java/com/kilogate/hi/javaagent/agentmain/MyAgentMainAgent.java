package com.kilogate.hi.javaagent.agentmain;

import java.lang.instrument.Instrumentation;

/**
 * MyAgentMainAgent
 *
 * @author kilogate
 * @create 2021/9/22 15:29
 **/
public class MyAgentMainAgent {
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("【agentmain】start");
        System.out.println("【agentmain】agentArgs: " + agentArgs);
        System.out.println("【agentmain】inst: " + inst);

        // 获取所有已加载的类
        Class[] allLoadedClasses = inst.getAllLoadedClasses();

        for (Class clazz : allLoadedClasses) {
            System.out.println("【agentmain】loaded: " + clazz);
        }
    }
}
