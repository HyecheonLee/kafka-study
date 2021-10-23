package com.hyecheon.kafkaconsumer.error.handler

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.listener.ConsumerAwareErrorHandler
import org.springframework.stereotype.Service
import java.lang.Exception

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/23
 */

class GlobalErrorHandler : ConsumerAwareErrorHandler {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun handle(thrownException: Exception, data: ConsumerRecord<*, *>, consumer: Consumer<*, *>) {
        log.warn("Global error handler for message : {}", data.value())
    }


}