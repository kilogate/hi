package com.kilogate.hi.logger.logback.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LogbackWithSlf4jUsage
 * logback + slf4j
 *
 * @author kilogate
 * @create 2021/1/20 23:05
 **/
public class LogbackWithSlf4jUsage {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LogbackWithSlf4jUsage.class);
        logger.debug("调试信息: {}", System.currentTimeMillis());
        logger.info("正常信息: {}", System.currentTimeMillis());
        logger.error("异常信息: {}", System.currentTimeMillis());

        Logger statisticsLogger = LoggerFactory.getLogger("statisticsLogger");
        statisticsLogger.info("统计信息: {}", System.currentTimeMillis());
    }
}
