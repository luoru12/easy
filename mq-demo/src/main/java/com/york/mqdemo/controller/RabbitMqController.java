package com.york.mqdemo.controller;

import com.york.mqdemo.producer.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMqController {

    @Autowired
    private RabbitProducer rabbitProducer;

    @GetMapping("/sendDemoQueue")
    public Object sendDemoQueue() {
        rabbitProducer.sendDemoQueue();
        return "success";
    }
    @GetMapping("/sendTopicQueue")
    public Object sendTopicQueue() {
        rabbitProducer.sendTopicQueue();
        return "success";
    }
    @GetMapping("/fanoutExchange")
    public Object fanoutExchange() {
        rabbitProducer.fanoutExchange();
        return "success";
    }
}
