package com.hyecheon.kafkaconsumer.entity

import kotlin.math.roundToInt
import kotlin.math.roundToLong

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/16
 */
data class Commodity(
    var name: String = "",
    var price: Double = 0.0,
    var measurement: String = "",
    var timestamp: Long = 0,
) {
    fun setPrice(price: Double) = run {
        this.copy(price = (price * 100).roundToInt().toDouble())
    }
}