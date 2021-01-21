package com.kilogate.hi.logger.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Log4j2Usage
 *
 * @author kilogate
 * @create 2021/1/21 16:37
 **/
public class Log4j2Usage {
    public static void main(String[] args) {
        //             <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>

        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        logger.debug("调试信息" + System.currentTimeMillis());
        logger.info("正常信息" + System.currentTimeMillis());
        logger.error("异常信息" + System.currentTimeMillis());
    }
}
