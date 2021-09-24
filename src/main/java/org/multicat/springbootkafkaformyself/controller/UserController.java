package org.multicat.springbootkafkaformyself.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wenzhi.huang huangwenzhi@leelen.cn
 * @since 2021/9/24 16:43
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private RedissonClient redissonClient;

    @GetMapping(value = "/lock")
    public void tryLock() {
        int count = 10;
        RLock lock = redissonClient.getLock("lock");
        while (count > 0) {
            if (lock.tryLock()) {
                log.info("lock {} times", count);
            }
            count--;
        }
        try {
            Thread.sleep(50 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
