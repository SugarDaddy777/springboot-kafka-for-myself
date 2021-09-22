package org.multicat.springbootkafkaformyself.controller;

import lombok.extern.slf4j.Slf4j;
import org.multicat.springbootkafkaformyself.watcher.Master;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author wenzhi.huang huangwenzhi@leelen.cn
 * @since 2021/9/9 19:58
 */
@Slf4j
@RestController
@RequestMapping(value = "/zk")
public class ZookeeperController {

    Master master;

    @GetMapping("/connectZK")
    public void connectZooKeeper() {
        try {
            master = new Master("159.75.2.217:2181");
            master.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/create")
    public void createZNode() {
        try {
            String serverId = Integer.toHexString(new Random().nextInt());
            log.info("serverId is {}", serverId);
            master.create(serverId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
