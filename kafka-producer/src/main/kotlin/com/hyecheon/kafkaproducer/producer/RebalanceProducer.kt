package com.hyecheon.kafkaproducer.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/20
 */
@Service
class RebalanceProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {
    private var i = 0


//    @Scheduled(fixedRate = 1000)
    fun sendMessage() = run {
        i++
        kafkaTemplate.send("t_rebalance", "Counter is $i")
    }
}