package com.kilogate.hi.java.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerUsage
 *
 * @author kilogate
 * @create 2022/1/10 17:20
 **/
@Slf4j
public class TimerUsage {
    public static void main(String[] args) {
        log.info("main start");

        Timer timer = new Timer(true);

        for (int i = 0; i < 10; i++) {
            timer.scheduleAtFixedRate(new MyTimerTask("task" + i, 0), 1000, 1000);
        }

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("main end");
    }

    @Slf4j
    @Getter
    @Setter
    @AllArgsConstructor
    private static class MyTimerTask extends TimerTask {
        private String name;
        private Integer round;

        @Override
        public void run() {
            log.info("{} run start: {}", this.getName(), this.getRound());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("{} run end: {}", this.getName(), this.getRound());

            this.setRound(this.getRound() + 1);
        }
    }
}
