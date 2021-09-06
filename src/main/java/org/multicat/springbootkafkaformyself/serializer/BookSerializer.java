package org.multicat.springbootkafkaformyself.serializer;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.multicat.springbootkafkaformyself.entity.Book;

import java.util.Map;

/**
 * @author wenzhi.huang huangwenzhi@leelen.cn
 * @since 2021/8/18 11:09
 */
public class BookSerializer implements Serializer<Book> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Book book) {
        return book.toString().getBytes();
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Book data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
