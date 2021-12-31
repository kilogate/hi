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
        test1();
    }

    // 快速上手
    private static void test1() {
        String bootstrapServers = "localhost:9092,localhost:9093,localhost:9094";
        String topic = "topic-demo";
        String clientId = "producer-demo";

        // 一、生产者配置
        Properties properties = new Properties();

        // brokers
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // key序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // value序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 客户端id
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        // 重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        // 两次重试之间的间隔，默认 100ms
        properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 100);
        // 分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, DefaultPartitioner.class.getName());
        // RecordAccumulator缓存大小，默认32M
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // RecordAccumulator缓存不足时 send 方法的超时时间，默认60s
        properties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 60000);
        // ProducerBatch的大小，默认16K
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // 每个连接最多只能缓存5个未响应的请求
        properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);
        // 元数据最大有效时间，过期后自动更新
        properties.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, 300000);
        // 分区中有多少副本收到消息生产者才认为写入成功：默认值 acks = 1，leader副本成功即可；acks = 0，零个副本成功即可；acks = -1 或 acks = all，所有副本成功才行
        properties.put(ProducerConfig.ACKS_CONFIG, "1");
        // 消息最大大小，默认 1M
        properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 1048576);
        // 消息压缩方式（压缩：以时间换空间），默认值 none：不压缩，gzip，snappy，lz4
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "none");
        // 连接最大空闲时间，默认540s
        properties.put(ProducerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, 540000);
        // 生产者发送 ProducerBatch 之前等待更多消息加入的时间，默认 0
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 0);
        // 消息接收缓冲区大小，默认 32K
        properties.put(ProducerConfig.RECEIVE_BUFFER_CONFIG, 32768);
        // 消息发送缓冲区大小，默认 128K
        properties.put(ProducerConfig.SEND_BUFFER_CONFIG, 131072);
        // 请求超时时间，默认30s
        properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        // KafkaProducer send 和 partitionsFor 方法最大阻塞时间，当生产者的发送缓冲区已满或者没有可用的元数据时，这些方法会阻塞
        properties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 60000);
        // 生产者拦截器链
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "");

        // 二、创建生产者实例
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        log.info("创建生产者实例完成");

        // 发送100条消息
        for (int i = 0; i < 100; i++) {
            // 消息体
            String value = "Message" + i;

            // 三、构建消息
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, value);

            // 四、发送消息
            Future<RecordMetadata> future = kafkaProducer.send(producerRecord);

            // 等待发送结果
            try {
                RecordMetadata recordMetadata = future.get();
                log.info("发送消息成功, topic: {}, partition: {}, offset: {}, timestamp: {}, value: {}",
                        recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), recordMetadata.timestamp(), value);

                Thread.sleep(1000);
            } catch (Exception e) {
                log.error("发送消息异常", e);
            }
        }

        // 五、关闭生产者实例
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
        String value = "MessageForAsyncSend";
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, value);

        // 异步发送
        kafkaProducer.send(producerRecord, (recordMetadata, e) -> {
            if (e != null) {
                log.error("异步发送消息异常", e);
            } else {
                log.info("异步发送消息成功, topic: {}, partition: {}, offset: {}, timestamp: {}, value: {}",
                        recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), recordMetadata.timestamp(), value);
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
        // 生产者拦截器链
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, MyProducerInterceptor.class.getName() + "," + MyProducerInterceptor.class.getName());

        // 创建生产者实例
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        log.info("创建生产者实例完成");

        // 构建消息
        String value = "MessageForProducerInterceptor";
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, value);

        // 发送消息
        try {
            RecordMetadata recordMetadata = kafkaProducer.send(producerRecord).get();
            log.info("发送消息成功, topic: {}, partition: {}, offset: {}, timestamp: {}, value: {}",
                    recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), recordMetadata.timestamp(), value);
        } catch (Exception e) {
            log.error("发送消息异常", e);
        }

        // 关闭生产者实例
        kafkaProducer.close();
    }

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
