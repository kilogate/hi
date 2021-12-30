package com.kilogate.hi.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * ProducerUsage
 *
 * @author kilogate
 * @create 2021/12/30 11:15
 **/
@Slf4j
public class ProducerUsage {
    public static void main(String[] args) {
        test1();
    }

    // 快速上手
    private static void test1() {
        String bootstrapServers = "localhost:9092,localhost:9093,localhost:9094";
        String topic = "topic-demo";

        // 生产者配置
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 创建生产者实例
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        log.info("创建生产者实例完成");

        // 发送100条消息
        for (int i = 0; i < 100; i++) {
            // 消息体
            String value = "Message" + i;

            // 构建消息
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, value);

            // 发送消息
            Future<RecordMetadata> future = kafkaProducer.send(producerRecord);

            // 等待发送结果
            try {
                RecordMetadata recordMetadata = future.get();
                log.info("发送结果: {}, 消息: {}", recordMetadata, value);
            } catch (Exception e) {
                log.error("发送消息异常", e);
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 关闭生产者实例
        kafkaProducer.close();
    }
}
