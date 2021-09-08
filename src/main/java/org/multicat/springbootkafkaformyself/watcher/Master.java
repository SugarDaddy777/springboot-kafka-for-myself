package org.multicat.springbootkafkaformyself.watcher;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author wenzhi.huang huangwenzhi@leelen.cn
 * @since 2021/9/8 16:24
 */
@Getter
@Setter
@Slf4j
public class Master implements Watcher {

    ZooKeeper zooKeeper;
    String host;

    public Master(String host) {
        this.host = host;
    }

    public void connect() throws IOException {
        zooKeeper = new ZooKeeper(this.host, 15 * 1000, this);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info(watchedEvent.toString());
    }
}
