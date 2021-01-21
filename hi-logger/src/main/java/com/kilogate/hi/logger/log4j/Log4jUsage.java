package com.kilogate.hi.logger.log4j;

import org.apache.log4j.Logger;

/**
 * Log4jUsage
 *
 * @author kilogate
 * @create 2021/1/21 12:09
 **/
public class Log4jUsage {
    /**
     * <dependency>
     * <groupId>log4j</groupId>
     * <artifactId>log4j</artifactId>
     * <version>1.2.17</version>
     * </dependency>
     */

    /**
     * log4j.properties
     */

    /**
     * test
     */
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Log4jUsage.class);
        logger.info("正常信息" + System.currentTimeMillis());
        logger.error("异常信息" + System.currentTimeMillis());

        Logger statisticsLogger = Logger.getLogger("statisticsLogger");
        statisticsLogger.info("统计信息" + System.currentTimeMillis());
    }
}
