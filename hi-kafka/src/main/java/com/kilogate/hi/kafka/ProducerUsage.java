package com.kilogate.hi.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;
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
        test3();
    }

    // 快速上手
    private static void test1() {
        String bootstrapServers = "localhost:9092,localhost:9093,localhost:9094";
        String topic = "topic-demo";
        String clientId = "producer-demo";

        // 生产者配置
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); // brokers
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // key序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // value序列化器
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, clientId); // 客户端id
        properties.put(ProducerConfig.RETRIES_CONFIG, 3); // 重试次数
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, DefaultPartitioner.class.getName()); // 分区器

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

    // 异步发送
    private static void test2() {
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

        // 构建消息
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "MessageForAsyncSend");

        // 异步发送
        kafkaProducer.send(producerRecord, (recordMetadata, e) -> {
            if (e != null) {
                log.error("异步发送消息异常", e);
            } else {
                log.info("异步发送消息成功, recordMetadata: {}", recordMetadata);
            }
        });

        // 关闭生产者实例
        kafkaProducer.close();
    }

    // 生产者拦截器
    private static void test3() {
        String bootstrapServers = "localhost:9092,localhost:9093,localhost:9094";
        String topic = "topic-demo";

        // 生产者配置
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, MyProducerInterceptor.class.getName());

        // 创建生产者实例
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        log.info("创建生产者实例完成");

        // 构建消息
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "MessageForProducerInterceptor");

        // 发送消息
        try {
            kafkaProducer.send(producerRecord).get();
        } catch (Exception e) {
            log.error("发送消息异常", e);
        }

        // 关闭生产者实例
        kafkaProducer.close();
    }

    /**
     * 生产者拦截器
     */
    @Slf4j
    public static class MyProducerInterceptor implements ProducerInterceptor {
        @Override
        public ProducerRecord onSend(ProducerRecord producerRecord) {
            log.info("onSend, producerRecord: {}", producerRecord);
            return producerRecord;
        }

        @Override
        public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
            log.info("onAcknowledgement, recordMetadata: {}, e: {}", recordMetadata, e);
        }

        @Override
        public void close() {
            log.info("close");
        }

        @Override
        public void configure(Map<String, ?> map) {
            log.info("configure");
        }
    }
}
