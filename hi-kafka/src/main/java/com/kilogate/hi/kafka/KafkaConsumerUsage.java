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
 * KafkaConsumerUsage
 *
 * @author kilogate
 * @create 2021/12/30 11:42
 **/
@Slf4j
public class KafkaConsumerUsage {
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
        // 是否开启自动提交，默认 true
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // 开启自动提交之后，自动定时提交的时间间隔，默认 5s
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 5000);
        // 没有消费位移时自动重置 offset，默认值 latest：从分区末尾开始消费，earliest：从头开始消费，none：不消费直接报错
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        // 请求超时时间，默认值 30s
        properties.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        // 消费者拦截器链，默认值为空
        properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, "");
        // 每次拉取最小字节数，默认 1B
        properties.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1);
        // 每次拉取最大字节数，默认 50M
        properties.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 52428800);
        // 每次拉取最大等待毫秒数，默认 500ms
        properties.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 500);
        // 每次拉取单分区最大字节数，默认 1M
        properties.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 1048576);
        // 每次拉取消息最多条数，默认 500
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);
        // 连接最大闲置时间，默认 9 分钟
        properties.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, 540000);
        // 使用 Pattern 方式订阅主题时是否忽略内部主题，默认值 true
        properties.put(ConsumerConfig.EXCLUDE_INTERNAL_TOPICS_CONFIG, true);
        // Socket 接收消息缓冲区大小，默认 64K
        properties.put(ConsumerConfig.RECEIVE_BUFFER_CONFIG, 65536);
        // Socket 发送消息缓冲区大小，默认 128K
        properties.put(ConsumerConfig.SEND_BUFFER_CONFIG, 131072);
        // 元数据过期时间，默认 5 分钟
        properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, 300000);
        // 重连之前的退避时间，默认 50ms
        properties.put(ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, 50);
        // 重试之前的退避时间，默认 100ms
        properties.put(ConsumerConfig.RETRY_BACKOFF_MS_CONFIG, 100);
        // 消费者的事务隔离级别，表示消费者所能消费到的位置
        // read_committed：忽略事务未提交的消息，只能消费到 LSO
        // read_uncommitted：默认值，可以消费到 HW
        properties.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_uncommitted");


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

    // 再均衡
    private static void test6() {
        // 开启一个消费者
        new Thread(() -> doTest6()).start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 再开启一个消费者触发再均衡
        new Thread(() -> doTest6()).start();
    }

    private static void doTest6() {
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

        // 三、订阅主题，同时设置再均衡监听器（可以使用再均衡监听器将消费位移存储到DB去，onPartitionsRevoked时入库，onPartitionsAssigned时查库并seek）
        Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
        kafkaConsumer.subscribe(Collections.singleton(topic), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                // 在再均衡开始之前和消费者停止消费之后被调用，一般用于处理消费位移提交，collection 表示再均衡之前分配的分区
                log.info("要开始再均衡操作了, 涉及分区: {}", collection);

                // 提交消费位移
                kafkaConsumer.commitSync(currentOffsets);
                currentOffsets.clear();
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                // 在再均衡完成之后和消费者开始消费之前被调用，collection 表示再均衡之后分配的分区
                log.info("再均衡操作完成了, 涉及分区: {}", collection);
            }
        });
        log.info("订阅主题 {} 完成，并设置了再均衡监听器", topic);

        // 四、循环消费消息100次
        int index = 0;
        while (index < 100) {
            index++;

            ConsumerRecords<Object, Object> records = kafkaConsumer.poll(Duration.ofMillis(1000));
            log.info("第 {} 次拉取了 {} 条消息", index, records.count());

            for (ConsumerRecord record : records) {
                log.info("收到消息, topic: {}, partition: {}, offset: {}, timestamp: {}, timestampType: {}, key: {}, value: {}",
                        record.topic(), record.partition(), record.offset(), record.timestamp(), record.timestampType(), record.key(), record.value());

                // 暂存消费位移
                currentOffsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1));
            }

            // 异步提交消费位移
            kafkaConsumer.commitAsync(currentOffsets, null);
        }

        // 五、提交消费位移
        // 自动提交

        // 六、关闭消费者实例
        kafkaConsumer.close();
    }

    // 消费者拦截器
    private static void test7() {
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
        // 消费者拦截器链，默认值为空
        properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, MyConsumerInterceptor.class.getName());

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
        }

        // 五、提交消费位移
        // 自动提交

        // 六、关闭消费者实例
        kafkaConsumer.close();
    }

    @Slf4j
    public static class MyConsumerInterceptor implements ConsumerInterceptor {
        @Override
        public ConsumerRecords onConsume(ConsumerRecords consumerRecords) {
            log.info("onConsume, consumerRecords: {}", consumerRecords);
            return consumerRecords;
        }

        @Override
        public void close() {
            log.info("close");
        }

        @Override
        public void onCommit(Map map) {
            log.info("onCommit, map: {}", map);
        }

        @Override
        public void configure(Map<String, ?> map) {
            log.info("configure, map: {}", map);
        }
    }
}
