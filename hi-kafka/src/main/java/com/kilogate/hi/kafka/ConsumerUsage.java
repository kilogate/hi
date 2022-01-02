package com.kilogate.hi.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

/**
 * ConsumerUsage
 *
 * @author kilogate
 * @create 2021/12/30 11:42
 **/
@Slf4j
public class ConsumerUsage {
    public static void main(String[] args) {
        test4();
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
}
