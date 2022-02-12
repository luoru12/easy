package com.york.mqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class RabbitConfig {

    @Primary
    @Bean
    public DirectExchange directExchange11111111111(){
        return new DirectExchange(Content.DIRECT_EXCHANGE);
    }

    @Bean
    public DirectExchange directExchange22(){
        return new DirectExchange(Content.DIRECT_EXCHANGE22);
    }

    /**
     * 定义demoQueue队列
     * @return
     */
    @Bean
    public Queue demoString() {
        return new Queue(Content.DEMO_QUEUE);
    }

    @Bean
    public Binding bindingExchange(Queue demoString,DirectExchange directExchange){
        return BindingBuilder.bind(demoString).to(directExchange).with(Content.DIRECT_EXCHANGE_KEY);
    }

    @Bean
    public Queue SeandString(){
        return new Queue(Content.SEND_QUEUE);
    }

    @Bean
    public Binding bindingExchange2(Queue demoString, DirectExchange directExchange11111111111){
        return BindingBuilder.bind(demoString).to(directExchange11111111111).with(Content.DIRECT_EXCHANGE_KEY);
    }
    @Bean
    public Binding bindingExchange3(Queue SeandString, DirectExchange directExchange22){
        return BindingBuilder.bind(SeandString).to(directExchange22).with(Content.DIRECT_EXCHANGE_KEY2);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(Content.TOPIC_EXCHANGE);
    }
    @Bean
    public Queue key1Queue() {
        return new Queue(Content.key1Queue);
    }
    @Bean
    public Queue key2Queue() {
        return new Queue(Content.key2Queue);
    }
    @Bean
    public Queue key3Queue() {
        return new Queue(Content.key3Queue);
    }
    @Bean
    public Queue key4Queue() {
        return new Queue(Content.key4Queue);
    }

    @Bean
    public Binding bindingExchange4(Queue key1Queue,TopicExchange topicExchange){
        return BindingBuilder.bind(key1Queue).to(topicExchange).with(Content.TOPIC_EXCHANGE_KEY);
    }
    @Bean
    public Binding bindingExchange5(Queue key2Queue,TopicExchange topicExchange){
        return BindingBuilder.bind(key2Queue).to(topicExchange).with(Content.TOPIC_EXCHANGE_KEY);
    }
    @Bean
    public Binding bindingExchange6(Queue key3Queue,TopicExchange topicExchange){
        return BindingBuilder.bind(key3Queue).to(topicExchange).with(Content.TOPIC_EXCHANGE_KEY);
    }
//    @Bean
//    public Binding bindingExchange7(Queue key4Queue,TopicExchange topicExchange){
//        return BindingBuilder.bind(key4Queue).to(topicExchange).with(Content.TOPIC_EXCHANGE_KEY);
//    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(Content.fanoutExchange);
    }

    @Bean
    public Binding bindingExchange8(Queue key4Queue,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(key4Queue).to(fanoutExchange);
    }
}
