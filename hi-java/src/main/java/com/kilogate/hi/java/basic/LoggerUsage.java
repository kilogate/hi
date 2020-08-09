package com.kilogate.hi.java.basic;

import java.util.logging.Logger;

/**
 * 日志的使用
 *
 * @author kilogate
 * @create 2020/8/8 下午10:17
 **/
public class LoggerUsage {
    private static final Logger logger = Logger.getLogger(LoggerUsage.class.getName());

    public static void main(String[] args) {
        logger.info("start");
        logger.info("end");
    }
}
