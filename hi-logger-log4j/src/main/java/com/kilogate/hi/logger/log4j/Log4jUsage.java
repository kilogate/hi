package com.kilogate.hi.logger.log4j;

import org.apache.log4j.Logger;

/**
 * Log4jUsage
 *
 * @author kilogate
 * @create 2021/1/21 12:09
 **/
public class Log4jUsage {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Log4jUsage.class);
        logger.debug("调试信息" + System.currentTimeMillis());
        logger.info("正常信息" + System.currentTimeMillis());
        logger.error("异常信息" + System.currentTimeMillis());

        Logger statisticsLogger = Logger.getLogger("statisticsLogger");
        statisticsLogger.info("统计信息" + System.currentTimeMillis());
    }
}
