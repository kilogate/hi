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
}
