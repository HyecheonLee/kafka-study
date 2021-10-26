package com.hyecheon.kafkaproducer.service

import com.hyecheon.kafkaproducer.entity.Invoice
import org.springframework.stereotype.Service
import java.util.concurrent.ThreadLocalRandom

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/24
 */
@Service
class InvoiceService {
    companion object {
        private var counter = 0
    }

    fun generateInvoice() = run {
        counter++
        Invoice("INV-$counter", ThreadLocalRandom.current().nextDouble(1.0, 1000.0), "USD")
    }
}