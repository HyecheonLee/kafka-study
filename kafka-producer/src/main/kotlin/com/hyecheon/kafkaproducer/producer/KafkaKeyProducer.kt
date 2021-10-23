package com.hyecheon.kafkaproducer.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/12
 */
@Service
class KafkaKeyProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {

    fun send(key: String, message: String) = run {
        kafkaTemplate.send("t_multi_partitions", key, message)
    }
}