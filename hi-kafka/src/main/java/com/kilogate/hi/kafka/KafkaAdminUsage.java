package com.kilogate.hi.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

/**
 * KafkaAdminUsage
 *
 * @author kilogate
 * @create 2022/1/5 20:33
 **/
@Slf4j
public class KafkaAdminUsage {
    public static void main(String[] args) {
        test2();
    }

    // 创建主题
    private static void test1() {
        String bootstrapServers = "localhost:9092,localhost:9093,localhost:9094";
        String topic = "topic-test1";

        // 一、配置
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);

        // 二、客户端
        AdminClient adminClient = AdminClient.create(properties);

        // 主题
        NewTopic newTopic = new NewTopic(topic, 4, (short) 3);

        // 三、创建主题
        CreateTopicsResult result = adminClient.createTopics(Collections.singleton(newTopic));

        // 同步等待执行结果
        try {
            result.all().get();
            log.info("创建主题成功");
        } catch (Exception e) {
            log.error("创建主题异常", e);
        }

        // 四、关闭客户端
        adminClient.close();
    }

    // 查看所有主题
    private static void test2() {
        String bootstrapServers = "localhost:9092,localhost:9093,localhost:9094";

        // 一、配置
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);

        // 二、客户端
        AdminClient adminClient = AdminClient.create(properties);

        // 三、查看所有主题
        ListTopicsResult listTopicsResult = adminClient.listTopics();

        // 同步等待执行结果
        try {
            Collection<TopicListing> topicListings = listTopicsResult.listings().get();
            log.info("所有主题: {}", topicListings);
        } catch (Exception e) {
            log.error("查看所有主题异常", e);
        }

        // 四、关闭客户端
        adminClient.close();
    }
}
