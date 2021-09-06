package org.multicat.springbootkafkaformyself.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.multicat.springbootkafkaformyself.asyn.MessageCallback;
import org.multicat.springbootkafkaformyself.entity.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.Future;

@Slf4j
@Service
public class BookProducerService {

    @Value("${spring.kafka.bootstrap-servers}")
    private String brokerServer;

    public String sendStringMessage(String message) {
        Book book = new Book();
        book.setId(1L);
        book.setName(message);
        ProducerRecord<String, Book> producerRecord = new ProducerRecord<>("my-topic", "index", book);
        Properties kafkaProperty = new Properties();
        kafkaProperty.put("bootstrap.servers", brokerServer);
        kafkaProperty.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperty.put("value.serializer", "org.multicat.springbootkafkaformyself.serializer.BookSerializer");
        try (KafkaProducer<String, Book> producer = new KafkaProducer<>(kafkaProperty)) {
            Future<RecordMetadata> future = producer.send(producerRecord, new MessageCallback());
            return future.get().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
