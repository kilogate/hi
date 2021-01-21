package com.kilogate.hi.logger.log4jWithJcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * log4j + commons-logging
 *
 * @author kilogate
 * @create 2021/1/21 16:52
 **/
public class Log4jWithJclUsage {
    /**
     * <dependency>
     * <groupId>commons-logging</groupId>
     * <artifactId>commons-logging</artifactId>
     * <version>1.2</version>
     * </dependency>
     * <p>
     * <dependency>
     * <groupId>log4j</groupId>
     * <artifactId>log4j</artifactId>
     * <version>1.2.17</version>
     * </dependency>
     */

    /**
     * common-logging.properties
     */

    /**
     * log4j.properties
     */

    /**
     * test
     */
    public static void main(String[] args) {
        Log logger = LogFactory.getLog(Log4jWithJclUsage.class);
        logger.debug("调试信息" + System.currentTimeMillis());
        logger.info("正常信息" + System.currentTimeMillis());
        logger.error("异常信息" + System.currentTimeMillis());
    }
}
