package com.kilogate.hi.logger.log4jWithSlf4j;

import com.kilogate.hi.logger.logbackWithSlf4j.LogbackUsage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log4j + slf4j
 *
 * @author kilogate
 * @create 2021/1/21 17:01
 **/
public class Log4jWithSlf4jUsage {
    /**
     * <dependency>
     * <groupId>org.slf4j</groupId>
     * <artifactId>slf4j-api</artifactId>
     * <version>1.7.25</version>
     * </dependency>
     *
     * <dependency>
     * <groupId>org.slf4j</groupId>
     * <artifactId>slf4j-log4j12</artifactId>
     * <version>1.7.25</version>
     * </dependency>
     */

    /**
     * log4j.properties
     */

    /**
     * test
     */
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LogbackUsage.class);
        logger.debug("调试信息: {}", System.currentTimeMillis());
        logger.info("正常信息: {}", System.currentTimeMillis());
        logger.error("异常信息: {}", System.currentTimeMillis());
    }
}
