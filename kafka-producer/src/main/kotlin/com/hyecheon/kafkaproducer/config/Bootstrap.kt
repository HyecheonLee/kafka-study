package com.hyecheon.kafkaproducer.config

import com.hyecheon.kafkaproducer.entity.Employee
import com.hyecheon.kafkaproducer.producer.EmployeeProducer
import com.hyecheon.kafkaproducer.producer.HelloKafkaProducer
import com.hyecheon.kafkaproducer.producer.KafkaKeyProducer
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
) {
/*    @EventListener(classes = [ApplicationStartedEvent::class])
    fun start() = run {
//        helloKafkaProducer.sendHello("Timotius ${Math.random()}")
        for (i in 1..10000) {
            val key = "key-${i % 4}"
            val message = "data $i with key ${key}"
            kafkaKeyProducer.send(key, message)
            Thread.sleep(500)
        }
    }*/

    @EventListener(classes = [ApplicationStartedEvent::class])
    fun start() = run {
        for (i in 1..5) {
            val employee = Employee("emp-${i}", "employee $i", LocalDate.now())
            employeeProducer.sendMessage(employee)
        }
    }
}