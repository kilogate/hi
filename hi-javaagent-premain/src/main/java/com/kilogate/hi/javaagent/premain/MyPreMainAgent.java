package com.kilogate.hi.javaagent.premain;

import java.lang.instrument.Instrumentation;

/**
 * MyPreMainAgent
 *
 * @author kilogate
 * @create 2021/9/22 14:51
 **/
public class MyPreMainAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("【premain】start");
        System.out.println("【premain】agentArgs: " + agentArgs);
        System.out.println("【premain】inst: " + inst);
    }
}
