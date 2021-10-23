package com.hyecheon.kafkaconsumer.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaconsumer.entity.CarLocation
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.adapter.RecordFilterStrategy

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
    fun consumerFactory() = run {
        val properties = kafkaProperties.buildConsumerProperties()
        properties[ConsumerConfig.METADATA_MAX_AGE_CONFIG] = "120000"
        DefaultKafkaConsumerFactory<Any, Any>(properties)
    }

    @Bean
    fun farLocationContainerFactory(
        configurer: ConcurrentKafkaListenerContainerFactoryConfigurer,
    ) = run {
        val objectMapper = ObjectMapper()
        ConcurrentKafkaListenerContainerFactory<Any, Any>().apply {
            configurer.configure(this, consumerFactory())
            setRecordFilterStrategy {
                try {
                    objectMapper.readValue(it.value().toString(), CarLocation::class.java).distance <= 100
                } catch (e: Exception) {
                    false
                }
            }
        }
    }
}