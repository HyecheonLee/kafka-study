package com.hyecheon.kafkaconsumer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaconsumer.entity.Commodity
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/16
 */
@Service
class CommodityNotificationConsumer {
    val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["t_commodity"], groupId = "cg-notification")
    fun consumer(message: String) = run {
        val commodity = objectMapper.readValue(message, Commodity::class.java)
        log.info("Notification logic for $commodity")
    }
}