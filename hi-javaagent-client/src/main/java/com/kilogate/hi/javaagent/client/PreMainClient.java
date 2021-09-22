package com.kilogate.hi.javaagent.client;

import java.util.Arrays;

/**
 * PreMainClient
 *
 * @author kilogate
 * @create 2021/9/22 14:54
 **/
public class PreMainClient {
    // java -javaagent:/Users/xxx/.m2/repository/com/kilogate/hi-javaagent-premain/1.0-SNAPSHOT/hi-javaagent-premain-1.0-SNAPSHOT.jar=helloagent com.kilogate.hi.javaagent.client.PreMainClient hellomain
    public static void main(String[] args) {
        System.out.println("【main】start");
        System.out.println("【main】args: " + Arrays.deepToString(args));
    }
}
