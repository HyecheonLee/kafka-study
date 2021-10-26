package com.hyecheon.kafkaconsumer.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaconsumer.entity.Invoice
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/24
 */
@Service
class InvoiceConsumer {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val objectMapper = ObjectMapper()

    @KafkaListener(topics = ["t_invoice"], containerFactory = "invoiceDltContainerFactory")
    fun consumer(message: String) = run {
        objectMapper.readValue(message, Invoice::class.java).apply {
            if (amount < 1) {
                throw IllegalArgumentException("Invalid amount: $amount for invoice $number")
            }
            log.info("Processing invoice : {}", this)
        }
    }
}