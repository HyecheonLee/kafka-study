package com.hyecheon.kafkaconsumer.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaconsumer.entity.CarLocation
import com.hyecheon.kafkaconsumer.error.handler.GlobalErrorHandler
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.TopicPartition
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer
import org.springframework.kafka.listener.SeekToCurrentErrorHandler
import org.springframework.retry.backoff.FixedBackOffPolicy
import org.springframework.retry.policy.SimpleRetryPolicy
import org.springframework.retry.support.RetryTemplate
import org.springframework.util.backoff.FixedBackOff

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

    @Bean(value = ["kafkaListenerContainerFactory"])
    fun kafkaListenerContainerFactory(
        configurer: ConcurrentKafkaListenerContainerFactoryConfigurer,
    ) = run {
        ConcurrentKafkaListenerContainerFactory<Any, Any>().apply {
            configurer.configure(this, consumerFactory())
            setErrorHandler(GlobalErrorHandler())
        }
    }

    private fun createRetryTemplate() = run {
        RetryTemplate().apply {
            setRetryPolicy(SimpleRetryPolicy(3))
            setBackOffPolicy(FixedBackOffPolicy().apply { backOffPeriod = 10_000 })
        }

    }

    @Bean(value = ["imageRetryContainerFactory"])
    fun imageRetryContainerFactory(configurer: ConcurrentKafkaListenerContainerFactoryConfigurer) = run {
        ConcurrentKafkaListenerContainerFactory<Any, Any>().apply {
            configurer.configure(this, consumerFactory())
            setErrorHandler(GlobalErrorHandler())
            setRetryTemplate(createRetryTemplate())
        }
    }

    @Bean(value = ["invoiceDltContainerFactory"])
    fun invoiceDltContainerFactory(
        configurer: ConcurrentKafkaListenerContainerFactoryConfigurer,
        kafkaTemplate: KafkaTemplate<Any, Any>,
    ) = run {
        ConcurrentKafkaListenerContainerFactory<Any, Any>().apply {
            configurer.configure(this, consumerFactory())
            val errorHandler = DeadLetterPublishingRecoverer(kafkaTemplate) { record, ex ->
                TopicPartition("t_invoice_dlt", record.partition())
            }
                .let { SeekToCurrentErrorHandler(it, FixedBackOff(1000, 1)) }
            setErrorHandler(errorHandler)
        }
    }
}