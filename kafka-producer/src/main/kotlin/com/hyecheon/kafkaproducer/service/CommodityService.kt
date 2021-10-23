package com.hyecheon.kafkaproducer.service

import com.hyecheon.kafkaproducer.entity.Commodity
import org.springframework.stereotype.Service
import java.util.concurrent.ThreadLocalRandom

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/16
 */
@Service
class CommodityService {

    companion object {
        private val COMMODITY_BASE = mutableMapOf<String, Commodity>()
        private const val COPPER = "copper"
        private const val GOLD = "gold"
        private const val MAX_ADJUSTMENT = 1.05
        private const val MIN_ADJUSTMENT = 0.95

        init {
            val timestamp = System.currentTimeMillis()
            COMMODITY_BASE[GOLD] = Commodity(GOLD, 1_407.25, "ounce", timestamp)
            COMMODITY_BASE[COPPER] = Commodity(COPPER, 5_900.57, "tonne", timestamp)
        }
    }

    fun createDummyCommodity(name: String) = run {
        val commodity = COMMODITY_BASE[name] ?: throw IllegalArgumentException("Invalid commodity $name")
        val basePrice = commodity.price ?: 0.0
        val newPrice = basePrice * ThreadLocalRandom.current().nextDouble(MIN_ADJUSTMENT, MAX_ADJUSTMENT)
        commodity.setPrice(newPrice).copy(timestamp = System.currentTimeMillis())
    }

    fun createDummyCommodities() = run {
        COMMODITY_BASE.map { entry: Map.Entry<String, Commodity> -> createDummyCommodity(entry.key) }
    }
}