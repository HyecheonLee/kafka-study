package com.hyecheon.kafkaconsumer.consumer

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/11
 */
//@Service
class HelloKafkaConsumer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {

    @KafkaListener(topics = ["t_hello"])
    fun consumer(message: String) = run {
        println(message)
    }
}