package com.hyecheon.kafkaproducer.entity

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/21
 */
data class CarLocation(
    var carId: String = "",
    var timestamp: Long? = null,
    var distance: Int = 0,
)