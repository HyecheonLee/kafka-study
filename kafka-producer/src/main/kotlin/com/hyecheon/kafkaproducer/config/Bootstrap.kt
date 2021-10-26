package com.hyecheon.kafkaproducer.config

import com.hyecheon.kafkaproducer.entity.Employee
import com.hyecheon.kafkaproducer.entity.FoodOrder
import com.hyecheon.kafkaproducer.entity.SimpleNumber
import com.hyecheon.kafkaproducer.producer.*
import com.hyecheon.kafkaproducer.service.ImageService
import com.hyecheon.kafkaproducer.service.InvoiceService
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.LocalDate

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/11
 */
@Component
class Bootstrap(
    private val helloKafkaProducer: HelloKafkaProducer,
    private val kafkaKeyProducer: KafkaKeyProducer,
    private val employeeProducer: EmployeeProducer,
    private val foodOrderProducer: FoodOrderProducer,
    private val simpleNumberProducer: SimpleNumberProducer,
    private val imageService: ImageService,
    private val imageProducer: ImageProducer,
    private val invoiceService: InvoiceService,
    private val invoiceProducer: InvoiceProducer,
) {
    //    @EventListener(classes = [ApplicationStartedEvent::class])
    fun start() = run {
//        helloKafkaProducer.sendHello("Timotius ${Math.random()}")
        for (i in 1..10000) {
            val key = "key-${i % 4}"
            val message = "data $i with key ${key}"
            kafkaKeyProducer.send(key, message)
            Thread.sleep(500)
        }
    }

    //    @EventListener(classes = [ApplicationStartedEvent::class])
    fun star2t() = run {
        for (i in 1..5) {
            val employee = Employee("emp-${i}", "employee $i", LocalDate.now())
            employeeProducer.sendMessage(employee)
        }
    }

    //    @EventListener(classes = [ApplicationStartedEvent::class])
    fun foodStart() = run {
        val chickenOrder = FoodOrder(3, "Chicken")
        val fishOrder = FoodOrder(10, "Fish")
        val pizzaOrder = FoodOrder(5, "Pizza")

        foodOrderProducer.send(chickenOrder)
        foodOrderProducer.send(fishOrder)
        foodOrderProducer.send(pizzaOrder)
    }

    //    @EventListener(classes = [ApplicationStartedEvent::class])
    fun simpleNumber() = run {
        for (i in 100..103) {
            val simpleNumber = SimpleNumber(i)
            simpleNumberProducer.send(simpleNumber)
        }
    }

    //    @EventListener(classes = [ApplicationStartedEvent::class])
    fun imageProducer() = run {
        var image1 = imageService.generateImage("jpg")
        var image2 = imageService.generateImage("svg")
        var image3 = imageService.generateImage("png")
        imageProducer.send(image1)
        imageProducer.send(image2)
        imageProducer.send(image3)
    }

    @EventListener(classes = [ApplicationStartedEvent::class])
    fun invoiceProducer() = run {
        for (i in 1..10) {
            val invoice = invoiceService.generateInvoice()
            invoiceProducer.send(if (i >= 5) invoice.copy(amount = -1.0) else invoice)
        }
    }
}