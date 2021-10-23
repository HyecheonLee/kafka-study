package com.hyecheon.kafkaproducer.producer

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaproducer.entity.CarLocation
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/21
 */

@Service
class CarLocationProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper,
) {
    fun send(carLocation: CarLocation) = run {
        val json = objectMapper.writeValueAsString(carLocation)
        kafkaTemplate.send("t_location", carLocation.carId, json)
    }
}