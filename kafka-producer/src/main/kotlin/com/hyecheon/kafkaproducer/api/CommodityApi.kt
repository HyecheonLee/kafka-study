package com.hyecheon.kafkaproducer.api

import com.hyecheon.kafkaproducer.service.CommodityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/16
 */
@RestController
@RequestMapping("/api/commodity/v1")
class CommodityApi(
    private val commodityService: CommodityService,
) {

    @GetMapping("/all")
    fun generateCommodities() = run {
        commodityService.createDummyCommodities()
    }

}