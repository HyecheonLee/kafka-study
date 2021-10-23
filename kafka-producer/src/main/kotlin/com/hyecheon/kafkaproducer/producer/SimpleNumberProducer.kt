package com.hyecheon.kafkaproducer.producer

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaproducer.entity.SimpleNumber
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/23
 */
@Service
class SimpleNumberProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {

    private val objectMapper = ObjectMapper()

    fun send(simpleNumber: SimpleNumber) = run {
        val json = objectMapper.writeValueAsString(simpleNumber)

        kafkaTemplate.send("t_simple_number", json)
    }
}