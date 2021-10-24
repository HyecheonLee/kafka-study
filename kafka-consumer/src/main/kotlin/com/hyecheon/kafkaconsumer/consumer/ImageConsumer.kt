package com.hyecheon.kafkaconsumer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaconsumer.entity.Image
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.net.http.HttpConnectTimeoutException

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/24
 */
@Service
class ImageConsumer {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val objectMapper = ObjectMapper()

    @KafkaListener(topics = ["t_image"], containerFactory = "imageRetryContainerFactory")
    fun consume(message: String) = run {
        val image = objectMapper.readValue(message, Image::class.java)
        if (image.type.lowercase() == "svg") {
            throw HttpConnectTimeoutException("Simulate failed API call")
        }
        log.info("Processing image : {}", image)
    }
}