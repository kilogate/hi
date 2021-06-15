package com.kilogate.hi.logger.log4j.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Log4jWithJclUsage
 * log4j + commons-logging
 *
 * @author kilogate
 * @create 2021/1/21 16:52
 **/
public class Log4jWithJclUsage {
    public static void main(String[] args) {
        Log logger = LogFactory.getLog(Log4jWithJclUsage.class);
        logger.debug("调试信息" + System.currentTimeMillis());
        logger.info("正常信息" + System.currentTimeMillis());
        logger.error("异常信息" + System.currentTimeMillis());

        Log statisticsLogger = LogFactory.getLog("statisticsLogger");
        statisticsLogger.info("统计信息" + System.currentTimeMillis());
    }
}
