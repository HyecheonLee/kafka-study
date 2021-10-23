package com.hyecheon.kafkaproducer.scheduler

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.hyecheon.kafkaproducer.entity.Commodity
import com.hyecheon.kafkaproducer.producer.CommodityProducer
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import java.lang.reflect.ParameterizedType

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/16
 */
@Service
class CommodityScheduler(
    private val commodityProducer: CommodityProducer,
    private val objectMapper: ObjectMapper,
) {
    private val restTemplate = RestTemplate()

//    @Scheduled(fixedRate = 5000)
    fun fetchCommodities() = run {
        object : ParameterizedTypeReference<List<Commodity>>() {}
        val commodities = restTemplate.exchange(
            "http://localhost:8080/api/commodity/v1/all", HttpMethod.GET, null,
            object : ParameterizedTypeReference<List<Commodity>>() {}
        ).body

        commodities?.forEach {
            commodityProducer.sendMessage(it)
        }
    }
}