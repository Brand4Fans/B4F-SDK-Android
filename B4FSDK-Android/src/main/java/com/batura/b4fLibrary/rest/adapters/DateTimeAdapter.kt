package com.batura.b4fLibrary.rest.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.joda.time.DateTime

internal class DateTimeAdapter {
    @ToJson
    fun toJson(card: DateTime): String {
        return card.toString()
    }

    @FromJson
    fun fromJson(dateTime: String): DateTime {
        return DateTime(dateTime)
    }
}