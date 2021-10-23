package com.hyecheon.kafkaproducer.producer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.hyecheon.kafkaproducer.entity.Commodity
import com.hyecheon.kafkaproducer.entity.Employee
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/14
 */
@Service
class CommodityProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper,
) {
    fun sendMessage(commodity: Commodity) = run {
        val json = objectMapper.writeValueAsString(commodity)
        kafkaTemplate.send("t_commodity", commodity.name ?: "", json)
    }
}