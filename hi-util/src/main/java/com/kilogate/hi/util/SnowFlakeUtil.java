package com.kilogate.hi.util;

/**
 * 雪花算法工具类
 * <p>
 * 格式：1位的固定值0，41位的时间戳，5位的数据中心id，5位的工作机器id，12位的序列号
 * https://blog.csdn.net/qq_33404395/article/details/89476607
 *
 * @author kilogate
 * @create 2020/12/14 16:30
 **/
public class SnowFlakeUtil {
    // ==================== 相关常量 ====================

    /**
     * 数据中心id所占的位数
     */
    private final long dataCenterIdBits = 5L;

    /**
     * 工作机器id所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 支持的最大数据中心id，结果是31
     */
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    /**
     * 支持的最大工作机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 工作机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据中心id向左移17位(12+5)
     */
    private final long dataCenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间戳向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /**
     * 开始时间戳（2015-01-01）
     */
    private final long twepoch = 1420041600000L;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    // ==================== 成员变量 ====================

    /**
     * 数据中心ID(0~31)
     */
    private long dataCenterId;

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    // ==================== 构造函数 ====================

    /**
     * 构造函数
     *
     * @param dataCenterId 数据中心ID（0~31）
     * @param workerId     工作机器ID（0~31）
     */
    public SnowFlakeUtil(long dataCenterId, long workerId) {
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }

        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    // ==================== 成员方法 ====================

    /**
     * 获得下一个ID（该方法是线程安全的）
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = generateTimestamp();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = blockUntilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long blockUntilNextMillis(long lastTimestamp) {
        long timestamp = generateTimestamp();

        while (timestamp <= lastTimestamp) {
            timestamp = generateTimestamp();
        }

        return timestamp;
    }

    protected long generateTimestamp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowFlakeUtil snowFlake = new SnowFlakeUtil(0, 0);

        for (int i = 0; i < 10; i++) {
            System.out.println(snowFlake.nextId());
        }
    }
}
