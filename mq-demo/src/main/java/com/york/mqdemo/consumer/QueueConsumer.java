package com.york.mqdemo.consumer;


import com.york.mqdemo.config.Content;
import com.york.mqdemo.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitListener(queues = Content.DEMO_QUEUE)
    public void recieved(String msg) {
        System.out.println("[demoQueue] recieved message: " + new String(msg));
    }

    @RabbitListener(queues = Content.SEND_QUEUE)
    public void recieved2(String msg) {
        System.out.println("[SEND_QUEUE] recieved message: " + new String(msg));
    }

    @RabbitListener(queues = Content.key1Queue)
    public void key1Queue(String msg) {
        System.out.println("[key1Queue] recieved message: " + new String(msg));
    }

    @RabbitListener(queues = Content.key2Queue)
    public void key2Queue(String msg) {
        System.out.println("[key2Queue] recieved message: " + new String(msg));
    }

    @RabbitListener(queues = Content.key3Queue)
    public void key3Queue(String msg) {
        System.out.println("[key3Queue] recieved message: " + new String(msg));
    }

    @RabbitListener(queues = Content.key4Queue)
    public void key4Queue(String msg) {
        System.out.println("[key4Queue] recieved message: " + new String(msg));
    }
}
