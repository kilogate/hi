package com.kilogate.hi.logger.logbackWithSlf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LogbackUsage
 *
 * @author kilogate
 * @create 2021/1/20 23:05
 **/
public class LogbackUsage {
    /**
     * <dependency>
     * <groupId>org.slf4j</groupId>
     * <artifactId>slf4j-api</artifactId>
     * <version>1.7.25</version>
     * </dependency>
     * <p>
     * <dependency>
     * <groupId>ch.qos.logback</groupId>
     * <artifactId>logback-classic</artifactId>
     * <version>1.2.3</version>
     * </dependency>
     */

    /**
     * logback.xml
     */

    /**
     * test
     */
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LogbackUsage.class);
        logger.debug("调试信息: {}", System.currentTimeMillis());
        logger.info("正常信息: {}", System.currentTimeMillis());
        logger.error("异常信息: {}", System.currentTimeMillis());

        Logger statisticsLogger = LoggerFactory.getLogger("statisticsLogger");
        statisticsLogger.info("统计信息: {}", System.currentTimeMillis());
    }
}
