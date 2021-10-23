package com.hyecheon.kafkaconsumer.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/12
 */
@Service
class KafkaKeyConsumer {

    private val log = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["t_multi_partitions"], concurrency = "3")
    fun consumer(message: ConsumerRecord<String, String>) = run {
        log.info("key : {}, partition : {}, message :{}", message.key(), message.partition(), message.value())
//        Thread.sleep(1000)
    }
}