package com.hyecheon.kafkaconsumer.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/20
 */
@Service
class RebalanceConsumer {
    private val log = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["t_rebalance"], concurrency = "3")
    fun consumer(message: ConsumerRecord<String, String>) = run {
        log.info("Partition : {}, Offset : {}, Message: {},", message.partition(), message.offset(), message.value())
    }
}