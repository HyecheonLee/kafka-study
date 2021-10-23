package com.hyecheon.kafkaproducer.producer

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/11
 */

@Service
class FixedRateProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {
    private val log = LoggerFactory.getLogger(this::class.java)
    private var i = 0

//    @Scheduled(fixedRate = 1000)
    fun sendMessage() = run {
        i++
        log.info("i is $i")
        kafkaTemplate.send("t_fixedrate", "Fixed rate $i")
    }
}