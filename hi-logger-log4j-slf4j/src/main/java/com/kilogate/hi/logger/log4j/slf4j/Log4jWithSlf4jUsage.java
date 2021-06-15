package com.kilogate.hi.logger.log4j.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Log4jWithSlf4jUsage
 * log4j + slf4j
 *
 * @author kilogate
 * @create 2021/1/21 17:01
 **/
public class Log4jWithSlf4jUsage {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Log4jWithSlf4jUsage.class);
        logger.debug("调试信息: {}", System.currentTimeMillis());
        logger.info("正常信息: {}", System.currentTimeMillis());
        logger.error("异常信息: {}", System.currentTimeMillis());

        Logger statisticsLogger = LoggerFactory.getLogger("statisticsLogger");
        statisticsLogger.info("统计信息" + System.currentTimeMillis());
    }
}
