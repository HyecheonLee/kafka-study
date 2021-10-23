package com.hyecheon.kafkaproducer.scheduler

import com.hyecheon.kafkaproducer.entity.CarLocation
import com.hyecheon.kafkaproducer.producer.CarLocationProducer
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/22
 */
@Service
class CarLocationScheduler(
    private val producer: CarLocationProducer,
) {
    lateinit var carOne: CarLocation
    lateinit var carTwo: CarLocation
    lateinit var carThree: CarLocation

    private val log = LoggerFactory.getLogger(this::class.java)

    init {
        val now = System.currentTimeMillis()

        carOne = CarLocation("car-one", now, 0)
        carTwo = CarLocation("car-two", now, 110)
        carThree = CarLocation("car-three", now, 95)
    }

    @Scheduled(fixedRate = 10000)
    fun generateCarLocation() = run {
        val now = System.currentTimeMillis()
        carOne = carOne.copy(distance = carOne.distance + 1, timestamp = now)
        carTwo = carTwo.copy(distance = carTwo.distance - 1, timestamp = now)
        carThree = carThree.copy(distance = carThree.distance + 1, timestamp = now)


        try {
            producer.send(carOne)
            log.info("Sent : {}", carOne)

            producer.send(carTwo)
            log.info("Sent : {}", carTwo)

            producer.send(carThree)
            log.info("Sent : {}", carThree)

        } catch (e: Exception) {
            log.warn("Error happend : {}", e)
        }
    }
}