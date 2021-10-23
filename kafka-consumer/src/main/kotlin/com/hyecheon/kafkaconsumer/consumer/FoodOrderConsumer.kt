package com.hyecheon.kafkaconsumer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaconsumer.entity.FoodOrder
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/23
 */
@Service
class FoodOrderConsumer {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val objectMapper = ObjectMapper()

    companion object {
        private const val MaxAmountOrder = 7
    }

//    @KafkaListener(topics = ["t_food_order"],errorHandler = "myFoodOrderErrorHandler")
    fun consume(message: String) = run {
        val foodOrder = objectMapper.readValue(message, FoodOrder::class.java)
        if (foodOrder.amount > MaxAmountOrder) {
            throw IllegalArgumentException("Food order amount too many")
        }
        log.info("Food order valid : {}", foodOrder)
    }
}