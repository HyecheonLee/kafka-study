package com.hyecheon.kafkaconsumer.entity

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/24
 */
data class Invoice(
    val number: String = "",
    val amount: Double = 0.0,
    val currency: String = "",
)