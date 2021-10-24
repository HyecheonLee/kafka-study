package com.hyecheon.kafkaproducer.service

import com.hyecheon.kafkaproducer.entity.Image
import org.springframework.stereotype.Service
import java.util.concurrent.ThreadLocalRandom

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/24
 */
@Service
class ImageService {

    companion object {
        private var counter = 0
    }

    fun generateImage(type: String) = run {
        counter++
        val name = "image-$counter"
        val size = ThreadLocalRandom.current().nextLong(100, 10_100)
        Image(name, size, type)
    }
}