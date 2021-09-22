package org.multicat.springbootkafkaformyself.controller;

import org.multicat.springbootkafkaformyself.service.BookConsumerService;
import org.multicat.springbootkafkaformyself.service.BookProducerService;
import org.multicat.springbootkafkaformyself.watcher.Master;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Resource
    private BookProducerService bookProducerService;
    @Resource
    private BookConsumerService bookConsumerService;

    @GetMapping("/test")
    public String sendMsgTest(String message) {
        return bookProducerService.sendStringMessage(message);
    }

    @GetMapping("/startConsume")
    public void startConsume() {
        bookConsumerService.consumeStringMessage();
    }

    @GetMapping("/shutdownConsume")
    public void shutdownConsume() {
        bookConsumerService.shutDownConsume();
    }

    @GetMapping("/rebuildConsumer")
    public void rebuildConsumer() {
        bookConsumerService.rebuildConsumer();
    }
}
