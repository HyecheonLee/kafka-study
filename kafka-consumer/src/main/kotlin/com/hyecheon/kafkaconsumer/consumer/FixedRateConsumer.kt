package com.hyecheon.kafkaconsumer.consumer

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/11
 */
//@Service
class FixedRateConsumer {
    private val log = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["t_fixedrate"])
    fun consumer(message: String) = run {
        log.info("consuming : ${message}")
    }
}