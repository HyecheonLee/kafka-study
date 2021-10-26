package com.hyecheon.kafkaproducer.producer

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaproducer.entity.Invoice
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/24
 */
@Service
class InvoiceProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {
    private val objectMapper = ObjectMapper()
    fun send(invoice: Invoice) = run {
        val json = objectMapper.writeValueAsString(invoice)
        kafkaTemplate.send("t_invoice", invoice.number, json)

    }
}