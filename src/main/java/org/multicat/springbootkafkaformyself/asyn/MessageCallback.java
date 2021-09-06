package org.multicat.springbootkafkaformyself.asyn;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author wenzhi.huang huangwenzhi@leelen.cn
 * @since 2021/8/17 20:06
 */
@Slf4j
public class MessageCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            e.printStackTrace();
        }
        log.info("Mark Log" + recordMetadata.toString());
    }
}
