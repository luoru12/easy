package com.york.mqdemo.producer;

import com.york.mqdemo.config.Content;
import com.york.mqdemo.config.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RabbitProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;


    public void sendDemoQueue() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
        System.out.println("[demoQueue] send msg: " + dateString);
        // 第一个参数为刚刚定义的队列名称
        this.amqpTemplate.convertAndSend(Content.DIRECT_EXCHANGE22,Content.DIRECT_EXCHANGE_KEY,dateString);
    }

    public void sendTopicQueue() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
        System.out.println("[demoQueue] send msg: " + dateString);
        // 第一个参数为刚刚定义的队列名称
        this.amqpTemplate.convertAndSend(Content.TOPIC_EXCHANGE,Content.key_1,dateString);
    }

    public void fanoutExchange() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
        System.out.println("[fanoutExchange] send msg: " + dateString);
        // 第一个参数为刚刚定义的队列名称
        this.amqpTemplate.convertAndSend(Content.fanoutExchange,"",dateString);
    }

}
