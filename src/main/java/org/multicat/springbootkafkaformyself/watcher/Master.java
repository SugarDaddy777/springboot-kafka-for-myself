package org.multicat.springbootkafkaformyself.watcher;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

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

    public void create(String serverId) throws InterruptedException {
        try {
            zooKeeper.create("/master", serverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            if (checkMasterStatus(serverId)) {
                return;
            } else {
                log.error("创建节点失败");
            }
        }
    }

    public boolean checkMasterStatus(String serverId) {
        Stat stat = new Stat();
        try {
            byte[] data = zooKeeper.getData("/master", false, stat);
            return serverId.equals(new String(data));
        } catch (KeeperException | InterruptedException e) {
            return false;
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info(watchedEvent.toString());
    }
}
