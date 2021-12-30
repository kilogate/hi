package com.kilogate.hi.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * ConsumerUsage
 *
 * @author kilogate
 * @create 2021/12/30 11:42
 **/
@Slf4j
public class ConsumerUsage {
    public static void main(String[] args) {
        test1();
    }

    // 快速上手
    private static void test1() {
        String bootstrapServers = "localhost:9092,localhost:9093,localhost:9094";
        String topic = "topic-demo";
        String groupId = "group-demo";

        // 消费者配置
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        // 创建消费者实例
        KafkaConsumer<Object, Object> kafkaConsumer = new KafkaConsumer<>(properties);
        log.info("创建消费者实例完成");

        // 订阅主题
        kafkaConsumer.subscribe(Collections.singleton(topic));
        log.info("订阅主题 {} 完成", topic);

        // 循环消费消息
        while (true) {
            ConsumerRecords<Object, Object> records = kafkaConsumer.poll(Duration.ofMillis(1000));
            log.info("收到 {} 条消息", records.count());

            for (ConsumerRecord record : records) {
                log.info("收到消息: {}", record.value());
            }
        }
    }
}