package com.kilogate.hi.javaagent.client;

import java.util.Date;

/**
 * AgentMainClient
 *
 * @author kilogate
 * @create 2021/9/22 15:32
 **/
public class AgentMainClient {
    public static void main(String[] args) {
        while (true) {
            System.out.println("now:" + new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
