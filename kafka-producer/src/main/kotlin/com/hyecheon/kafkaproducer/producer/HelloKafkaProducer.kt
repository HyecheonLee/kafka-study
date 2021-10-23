package com.hyecheon.kafkaproducer.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class HelloKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {


    fun sendHello(message: String) = run {
        kafkaTemplate.send("t_hello", "Hello $message")
    }
}