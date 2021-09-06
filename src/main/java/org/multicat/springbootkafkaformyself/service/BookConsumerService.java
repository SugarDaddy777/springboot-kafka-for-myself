package org.multicat.springbootkafkaformyself.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

/**
 * @author admin
 */
@Slf4j
@Service
public class BookConsumerService {

    @Value("${spring.kafka.bootstrap-servers}")
    private String brokerServer;

    private KafkaConsumer<String, String> kafkaConsumer;

    @PostConstruct
    private void init() {
        Properties kafkaProperty = new Properties();
        kafkaProperty.put("bootstrap.servers", brokerServer);
        kafkaProperty.put("group.id", "BookCounter");
        kafkaProperty.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProperty.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConsumer = new KafkaConsumer<>(kafkaProperty);
        kafkaConsumer.subscribe(Collections.singleton("my-topic"));
    }

    public void rebuildConsumer() {
        init();
    }

    public void consumeStringMessage() {
        CompletableFuture.runAsync(this::consumeStringMessageAsync);
    }

    public void consumeStringMessageAsync() {
        try {
            while (true) {
                Map<String, Object> castBookMap = new HashMap<>(16);
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : consumerRecords) {
                    log.debug("topic = %s, partition = %s, offset = %d, customer = %s, country = %s\n", record.topic(),
                            record.partition(), record.offset(), record.key(), record.value());
                    int updateCount = 1;
                    if (castBookMap.containsValue(record.value())) {
                        updateCount = (int) castBookMap.get(record.value()) + 1;
                    }
                    castBookMap.put(record.value(), updateCount);
                    JSONObject object = new JSONObject(castBookMap);
                    kafkaConsumer.commitSync();
                    System.out.println(object);
                }
            }
        } catch (WakeupException e) {
            kafkaConsumer.close();
        } finally {
            kafkaConsumer.close();
        }
    }

    public void shutDownConsume() {
        kafkaConsumer.wakeup();
    }
}
