package com.kilogate.hi.logger.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LogbackUsage
 *
 * @author kilogate
 * @create 2021/1/20 23:05
 **/
public class LogbackUsage {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackUsage.class);

    public static void main(String[] args) {
        LOGGER.info("start, now: {}", System.currentTimeMillis());
    }
}
