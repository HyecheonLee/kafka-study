package com.hyecheon.kafkaconsumer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaconsumer.entity.CarLocation
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/22
 */
@Service
class CarLocationConsumer(
) {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val objectMapper: ObjectMapper = ObjectMapper()

    @KafkaListener(topics = ["t_location"], groupId = "cg-all-location")
    fun listenAll(message: String) = run {

        try {
            val carLocation = objectMapper.readValue(message, CarLocation::class.java)
            log.info("listenAll : {}", carLocation)
        } catch (e: Exception) {
            log.error(e.message)
        }
    }

    @KafkaListener(topics = ["t_location"],
        groupId = "cg-far-location",
        containerFactory = "farLocationContainerFactory")
    fun listenFar(message: String) = run {
        try {
            val carLocation = objectMapper.readValue(message, CarLocation::class.java)
//        if (carLocation.distance < 100) return@run
            log.info("listenFar : {}", carLocation)
        } catch (e: Exception) {
            log.error(e.message)
        }
    }

}