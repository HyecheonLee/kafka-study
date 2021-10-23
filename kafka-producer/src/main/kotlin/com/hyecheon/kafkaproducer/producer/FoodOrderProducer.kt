package com.hyecheon.kafkaproducer.producer

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaproducer.entity.FoodOrder
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/23
 */
@Service
class FoodOrderProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {
    private val objectMapper: ObjectMapper = ObjectMapper()


    fun send(foodOrder: FoodOrder) = run {
        val json = objectMapper.writeValueAsString(foodOrder)
        kafkaTemplate.send("t_food_order", json)

    }
}