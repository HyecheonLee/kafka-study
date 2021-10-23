package com.hyecheon.kafkaproducer.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/20
 */
@Configuration
class KafkaConfig(
    private val kafkaProperties: KafkaProperties,
) {

    @Bean
    fun producerFactory() = run {
        val properties = kafkaProperties.buildProducerProperties()

        properties[ProducerConfig.METADATA_MAX_AGE_CONFIG] = "180000"

        DefaultKafkaProducerFactory<String, String>(properties)
    }

    @Bean
    fun kafkaTemplate() = run {
        KafkaTemplate<String, String>(producerFactory())
    }
}