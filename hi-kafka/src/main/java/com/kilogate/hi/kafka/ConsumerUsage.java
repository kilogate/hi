package com.kilogate.hi.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ConsumerUsage
 *
 * @author kilogate
 * @create 2021/12/30 11:42
 **/
@Slf4j
public class ConsumerUsage {
    public static void main(String[] args) {
        test5();
    }

    // 快速上手
    private static void test1() {
        String bootstrapServers = "localhost:9092,localhost:9093,localhost:9094";
        String topic = "topic-demo";
        String groupId = "group-demo";
        String clientId = "consumer-demo";

        // 一、消费者配置
        Properties properties = new Properties();

        // brokers
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // key反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // value反序列化器
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 消费组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        // 客户端id
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);
        // 是否开启自动提交，默认 true
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // 开启自动提交之后，自动定时提交的时间间隔，默认 5s
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 5000);
        // 没有消费位移时自动重置 offset，默认值 latest：从分区末尾开始消费，earliest：从头开始消费，none：不消费直接报错
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        // 请求超时时间，默认值 30s
        properties.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);

        // 二、创建消费者实例
        KafkaConsumer<Object, Object> kafkaConsumer = new KafkaConsumer<>(properties);
        log.info("创建消费者实例完成");

        // 三、订阅主题
        kafkaConsumer.subscribe(Collections.singleton(topic));
        log.info("订阅主题 {} 完成", topic);

        // 四、循环消费消息100次
        int index = 0;
        while (index < 100) {
            index++;

            ConsumerRecords<Object, Object> records = kafkaConsumer.poll(Duration.ofMillis(1000));
            log.info("本次拉取了 {} 条消息", records.count());

            for (ConsumerRecord record : records) {
                log.info("收到消息, topic: {}, partition: {}, offset: {}, timestamp: {}, timestampType: {}, key: {}, value: {}",
                        record.topic(), record.partition(), record.offset(), record.timestamp(), record.timestampType(), record.key(), record.value());
            }
        }

        // 五、提交消费位移
        // 自动提交

        // 六、关闭消费者实例
        kafkaConsumer.close();
    }

    // 订阅分区
    private static void test2() {
        String bootstrapServers = "localhost:9092,localhost:9093,localhost:9094";
        String topic = "topic-demo";
        String groupId = "group-demo";
        String clientId = "consumer-demo";

        // 一、消费者配置
        Properties properties = new Properties();

        // brokers
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // key反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // value反序列化器
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 消费组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        // 客户端id
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);

        // 二、创建消费者实例
        KafkaConsumer<Object, Object> kafkaConsumer = new KafkaConsumer<>(properties);
        log.info("创建消费者实例完成");

        // 三、订阅分区

        // 查询分区信息
        List<PartitionInfo> partitionInfos = kafkaConsumer.partitionsFor(topic);

        // 只订阅第一个分区
        PartitionInfo partitionInfo = partitionInfos.get(0);
        TopicPartition topicPartition = new TopicPartition(partitionInfo.topic(), partitionInfo.partition());
        Collection<TopicPartition> partitions = Collections.singleton(topicPartition);
        kafkaConsumer.assign(partitions);

        log.info("订阅分区 {} 完成", topicPartition);

        // 四、循环消费消息100次
        int index = 0;
        while (index < 100) {
            index++;

            ConsumerRecords<Object, Object> records = kafkaConsumer.poll(Duration.ofMillis(1000));
            log.info("本次拉取了 {} 条消息", records.count());

            for (ConsumerRecord record : records) {
                log.info("收到消息, topic: {}, partition: {}, offset: {}, timestamp: {}, timestampType: {}, key: {}, value: {}",
                        record.topic(), record.partition(), record.offset(), record.timestamp(), record.timestampType(), record.key(), record.value());
            }
        }

        // 五、提交消费位移
        // 自动提交

        // 六、关闭消费者实例
        kafkaConsumer.close();
    }

    // 位移提交
    private static void test3() {
        String bootstrapServers = "localhost:9092,localhost:9093,localhost:9094";
        String topic = "topic-demo";
        String groupId = "group-demo";
        String clientId = "consumer-demo";

        // 一、消费者配置
        Properties properties = new Properties();

        // brokers
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // key反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // value反序列化器
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 消费组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        // 客户端id
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);

        // 二、创建消费者实例
        KafkaConsumer<Object, Object> kafkaConsumer = new KafkaConsumer<>(properties);
        log.info("创建消费者实例完成");

        // 三、订阅分区

        // 查询分区信息
        List<PartitionInfo> partitionInfos = kafkaConsumer.partitionsFor(topic);

        // 第一个分区
        PartitionInfo partitionInfo = partitionInfos.get(0);

        // 只订阅第一个分区
        TopicPartition topicPartition = new TopicPartition(partitionInfo.topic(), partitionInfo.partition());
        Collection<TopicPartition> partitions = Collections.singleton(topicPartition);
        kafkaConsumer.assign(partitions);

        log.info("订阅分区 {} 完成", topicPartition);

        // 四、消费消息
        ConsumerRecords<Object, Object> records = ConsumerRecords.empty();
        while (records.isEmpty()) {
            records = kafkaConsumer.poll(Duration.ofMillis(1000));
            log.info("本次拉取了 {} 条消息", records.count());
        }

        for (ConsumerRecord record : records) {
            log.info("收到消息, topic: {}, partition: {}, offset: {}, timestamp: {}, timestampType: {}, key: {}, value: {}",
                    record.topic(), record.partition(), record.offset(), record.timestamp(), record.timestampType(), record.key(), record.value());
        }

        // 消费的最后一条消费的offset
        List<ConsumerRecord<Object, Object>> partitionRecords = records.records(topicPartition);
        long lastConsumedOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
        log.info("lastConsumedOffset: {}", lastConsumedOffset);

        // 五、同步提交消费位移
        kafkaConsumer.commitSync();

        // 已提交的offset
        OffsetAndMetadata committed = kafkaConsumer.committed(topicPartition);
        long committedOffset = committed.offset();
        log.info("committedOffset: {}", committedOffset);

        // 下一条要消费的消息的offset
        long position = kafkaConsumer.position(topicPartition);
        log.info("position: {}", position);

        // 六、关闭消费者实例
        kafkaConsumer.close();
    }

    // 暂停与恢复消费
    private static void test4() {
        String bootstrapServers = "localhost:9092,localhost:9093,localhost:9094";
        String topic = "topic-demo";
        String groupId = "group-demo";

        // 一、消费者配置
        Properties properties = new Properties();

        // brokers
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // key反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // value反序列化器
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 消费组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        // 二、创建消费者实例
        KafkaConsumer<Object, Object> kafkaConsumer = new KafkaConsumer<>(properties);
        log.info("创建消费者实例完成");

        // 三、订阅主题
        kafkaConsumer.subscribe(Collections.singleton(topic));
        log.info("订阅主题 {} 完成", topic);

        // 获取分区信息
        PartitionInfo partitionInfo = kafkaConsumer.partitionsFor(topic).get(0);
        Set<TopicPartition> topicPartitions = Collections.singleton(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));

        // 四、循环消费消息100次
        int index = 0;
        while (index < 100) {
            index++;

            ConsumerRecords<Object, Object> records = kafkaConsumer.poll(Duration.ofMillis(1000));
            log.info("第 {} 次拉取了 {} 条消息", index, records.count());

            for (ConsumerRecord record : records) {
                log.info("收到消息, topic: {}, partition: {}, offset: {}, timestamp: {}, timestampType: {}, key: {}, value: {}",
                        record.topic(), record.partition(), record.offset(), record.timestamp(), record.timestampType(), record.key(), record.value());
            }

            if (index == 20) {
                // 暂停一个分区的消费
                kafkaConsumer.pause(topicPartitions);
                log.info("暂停分区 {} 完成", partitionInfo.partition());

                // 获取被暂停的分区
                Set<TopicPartition> paused = kafkaConsumer.paused();
                log.info("被暂停的分区: {}", paused);
            }

            if (index == 40) {
                // 恢复该分区的消费
                kafkaConsumer.resume(topicPartitions);
                log.info("恢复分区 {} 完成", partitionInfo.partition());
            }
        }

        // 五、提交消费位移
        // 自动提交

        // 六、关闭消费者实例
        kafkaConsumer.close();
    }

    // 指定位移消费
    private static void test5() {
        String bootstrapServers = "localhost:9092,localhost:9093,localhost:9094";
        String topic = "topic-demo";
        String groupId = "group-demo";

        // 一、消费者配置
        Properties properties = new Properties();

        // brokers
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // key反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // value反序列化器
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 消费组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        // 二、创建消费者实例
        KafkaConsumer<Object, Object> kafkaConsumer = new KafkaConsumer<>(properties);
        log.info("创建消费者实例完成");

        // 三、订阅主题
        kafkaConsumer.subscribe(Collections.singleton(topic));
        log.info("订阅主题 {} 完成", topic);

        // 四、循环消费消息100次
        int index = 0;
        while (index < 100) {
            index++;

            ConsumerRecords<Object, Object> records = kafkaConsumer.poll(Duration.ofMillis(1000));
            log.info("第 {} 次拉取了 {} 条消息", index, records.count());

            for (ConsumerRecord record : records) {
                log.info("收到消息, topic: {}, partition: {}, offset: {}, timestamp: {}, timestampType: {}, key: {}, value: {}",
                        record.topic(), record.partition(), record.offset(), record.timestamp(), record.timestampType(), record.key(), record.value());
            }

            if (index == 1) {
                // 获取分配到的分区
                Set<TopicPartition> topicPartitions = kafkaConsumer.assignment();

                // 获取一天前各分区最新消息的位置
                Map<TopicPartition, Long> timestampsToSearch = topicPartitions.stream()
                        .collect(Collectors.toMap(Function.identity(), tp -> System.currentTimeMillis() - 24 * 3600 * 1000));
                Map<TopicPartition, OffsetAndTimestamp> topicPartitionOffsetAndTimestampMap = kafkaConsumer.offsetsForTimes(timestampsToSearch);
                log.info("一天前各分区最新消息的位置: {}", topicPartitionOffsetAndTimestampMap);

                // 获取分区末尾消息的位置
                Map<TopicPartition, Long> topicPartitionOffsetMap = kafkaConsumer.endOffsets(topicPartitions);
                // 获取分区开始消息的位置，日志清理之后不为 0
//                Map<TopicPartition, Long> topicPartitionOffsetMap = kafkaConsumer.beginningOffsets(topicPartitions);

                // 指定消费位移：只能对已分配到的分区指定消费位移，分配分区的操作在 poll 方法中
                topicPartitions.forEach(topicPartition -> kafkaConsumer.seek(topicPartition, topicPartitionOffsetMap.get(topicPartition) - 3));
                log.info("指定消费位移 -3 完成，涉及的分区: {}", topicPartitions);

                // 从头开始消费
//                kafkaConsumer.seekToBeginning(topicPartitions);
                // 从尾开始消费
//                kafkaConsumer.seekToEnd(topicPartitions);
            }
        }

        // 五、提交消费位移
        // 自动提交

        // 六、关闭消费者实例
        kafkaConsumer.close();
    }
}
