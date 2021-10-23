package com.hyecheon.kafkaproducer.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.hyecheon.kafkaconsumer.json.CustomLocalDateDeSerializer
import com.hyecheon.kafkaproducer.json.CustomLocalDateSerializer
import java.time.LocalDate

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/14
 */
data class Employee(
    var employeeId: String = "",
    var name: String = "",
    @field:JsonProperty("birth_date")
    @field:JsonDeserialize(using = CustomLocalDateDeSerializer::class)
    @field:JsonSerialize(using = CustomLocalDateSerializer::class)
    var birthDate: LocalDate? = null,
)
