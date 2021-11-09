package org.multicat.springbootkafkaformyself.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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
    public int tryLock() throws InterruptedException {
        int count = 10;
        RLock lock = redissonClient.getLock("lock");
        while (count > 0) {
            if (lock.tryLock(20, 20, TimeUnit.SECONDS)) {
                log.info("lock {} times", count);
            }
            count--;
        }
        return 1;
    }

    @GetMapping(value = "/seizeLock")
    public int seizeLock() {
        int count = 7;
        RLock lock = redissonClient.getLock("lock");
        while (count > 0) {
            lock.lock();
            log.info("lock {} times", count);
            count--;
        }
        return 2;
    }

    @GetMapping(value = "/makeDeadLock")
    public void makeDeadLock() {
        Object a = new Object();
        Object b = new Object();
        new Thread(()->{
            synchronized (a){
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                }
                synchronized (b){

                }
            }
        }).start();
        new Thread(()->{
            synchronized (b){
                synchronized (a){

                }
            }
        }).start();
    }

}
