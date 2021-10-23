package com.hyecheon.kafkaproducer.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * User: hyecheon lee
 * Email: rainbow880616@gmail.com
 * Date: 2021/10/14
 */
class CustomLocalDateSerializer() : StdSerializer<LocalDate>(LocalDate::class.java) {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }

    override fun serialize(value: LocalDate, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(formatter.format(value))
    }
}