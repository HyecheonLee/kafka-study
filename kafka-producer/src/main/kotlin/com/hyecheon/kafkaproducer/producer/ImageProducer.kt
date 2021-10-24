package com.hyecheon.kafkaproducer.producer

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaproducer.entity.Image
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/24
 */
@Service
class ImageProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {
    private val objectMapper = ObjectMapper()

    fun send(image: Image) = run {
        val json = objectMapper.writeValueAsString(image)
        kafkaTemplate.send("t_image", image.type, json)
    }
}