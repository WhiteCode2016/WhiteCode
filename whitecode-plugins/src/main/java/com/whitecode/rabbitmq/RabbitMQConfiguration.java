package com.whitecode.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ消息队列 配置
 * Created by White on 2017/9/27.
 */
@Configuration
public class RabbitMQConfiguration {

    public static final String QUEUE_NAME = "spring-boot";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }
}
