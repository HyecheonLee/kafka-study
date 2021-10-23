package com.hyecheon.kafkaproducer.entity

import kotlin.math.round
import kotlin.math.roundToInt
import kotlin.math.roundToLong

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/16
 */
data class Commodity(
    var name: String? = null,
    var price: Double = 0.0,
    var measurement: String? = null,
    var timestamp: Long? = null,
) {
    fun setPrice(price: Double) = run {
        this.copy(price = round(price * 100) / 100)
    }
}