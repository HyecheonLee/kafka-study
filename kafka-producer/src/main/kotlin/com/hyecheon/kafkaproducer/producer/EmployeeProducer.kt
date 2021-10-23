package com.hyecheon.kafkaproducer.producer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.hyecheon.kafkaproducer.entity.Employee
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/14
 */
@Service
class EmployeeProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {
    private val objectMapper = ObjectMapper().registerModule(JavaTimeModule())
    fun sendMessage(employee: Employee) = run {
        val json = objectMapper.writeValueAsString(employee)
        kafkaTemplate.send("t_employee", json)
    }
}