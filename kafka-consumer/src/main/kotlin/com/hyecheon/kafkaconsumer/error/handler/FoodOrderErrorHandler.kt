package com.hyecheon.kafkaconsumer.error.handler

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.listener.ConsumerAwareErrorHandler
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler
import org.springframework.kafka.listener.ListenerExecutionFailedException
import org.springframework.messaging.Message
import org.springframework.stereotype.Service
import java.lang.Exception

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/23
 */
@Service(value = "myFoodOrderErrorHandler")
class FoodOrderErrorHandler : ConsumerAwareListenerErrorHandler {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun handleError(
        message: Message<*>, exception: ListenerExecutionFailedException, consumer: Consumer<*, *>,
    ) = run {
        log.warn("Food order error. Pretending to send to elasticsearch: {}, because: {}",
            message.payload,
            exception.message)
        if (exception.cause is RuntimeException) {
            throw exception
        }
    }
}