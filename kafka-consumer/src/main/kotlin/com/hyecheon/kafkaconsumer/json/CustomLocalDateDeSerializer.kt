package com.hyecheon.kafkaconsumer.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/14
 */
class CustomLocalDateDeSerializer : StdDeserializer<LocalDate>(LocalDate::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDate {
        return LocalDate.parse(p.readValueAs(String::class.java), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }
}