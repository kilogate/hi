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
        // todo ThresholdFilter
        // <!--过滤掉spring和mybatis的一些无用的DEBUG信息 -->
//        <logger name="org.springframework" level="INFO"></logger>

//                    <!--控制台只输出level及以上级别的信息（onMatch），其他的直接拒绝（onMismatch） -->
//
//                    <!-- DENY，日志将立即被抛弃不再经过其他过滤器； NEUTRAL，有序列表里的下个过滤器过接着处理日志； ACCEPT，日志会被立即处理，不再经过剩余过滤器。 -->

        // additivity

        Logger logger = LoggerFactory.getLogger(LogbackUsage.class);
        logger.info("正常日志: {}", System.currentTimeMillis());
        logger.error("异常日志: {}", System.currentTimeMillis());

        Logger statisticsLogger = LoggerFactory.getLogger("statisticsLogger");
        statisticsLogger.info("统计信息: {}", System.currentTimeMillis());
    }
}
