package com.hyecheon.kafkaconsumer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaproducer.entity.Employee
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/14
 */
@Service
class EmployJsonConsumer {
    private val log = LoggerFactory.getLogger(this::class.java)
    val objectMapper = ObjectMapper()

    @KafkaListener(topics = ["t_employee"])
    fun consume(message: String) = run {
        try {
            val employee = objectMapper.readValue(message, Employee::class.java)
            log.info("Employee {}", employee)
        } catch (e: Exception) {

        }
    }
}