package com.hyecheon.kafkaconsumer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaconsumer.entity.SimpleNumber
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/23
 */
@Service
class SimpleNumberConsumer {
    private val log = LoggerFactory.getLogger(this::class.java)

    private val objectManager = ObjectMapper()

    @KafkaListener(topics = ["t_simple_number"])
    fun consume(message: String) = run {
        val simpleNumber = objectManager.readValue(message, SimpleNumber::class.java)

        if (simpleNumber.number % 2 != 0) {
            throw IllegalArgumentException("Odd number")
        }

        log.info("Valid number : {}", simpleNumber)

    }
}