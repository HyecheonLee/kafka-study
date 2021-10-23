package com.hyecheon.kafkaconsumer.entity

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/23
 */
data class FoodOrder(
    val amount: Int = 0,
    val item: String = "",
)