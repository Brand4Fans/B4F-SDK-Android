package com.batura.b4fLibrary.api

import okhttp3.Request
import okio.Buffer
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import retrofit2.Response
import java.io.IOException
import java.util.*

/**
 * @suppress
 */
fun Response<*>.getHeaderdate() : DateTime {
    return try {
        DateTimeFormat
            .forPattern("EEE, dd MMM yyyy HH:mm:ss zzz")
            .withLocale(Locale.ENGLISH)
            .parseDateTime(headers().get("Date"))
    }catch (exception : Throwable){
        DateTime.now()
    }
}

/**
 * @suppress
 */
fun Request.generateLog() : String{
    val result = StringBuilder()
    return result.append("Headers : ")
        .append(headers())
        .append("\n")
        .append("Method: ")
        .append(method())
        .append(" ")
        .append( url() )
        .append("\n")
        .append("Body : ")
        .append(bodyToString()).toString()
}

/**
 * @suppress
 */
fun Request.bodyToString(): String? {
    return try {
        val copy = newBuilder().build()
        val buffer = Buffer()
        copy.body()?.writeTo(buffer)
        buffer.readUtf8()
    } catch (e: IOException) {
        "did not work"
    }
}
