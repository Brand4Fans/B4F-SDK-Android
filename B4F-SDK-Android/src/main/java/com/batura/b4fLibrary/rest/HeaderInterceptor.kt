package com.batura.b4fLibrary.rest

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import java.io.IOException

internal class HeaderInterceptor(
        private val retrofitRest: RetrofitRest
    ) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {

        val requestBuilder = chain.request()
                .newBuilder()
                .addHeader("X-API-KEY", retrofitRest.apiKey)
                .addHeader("Accept-Language", "es_ES")
        if (!chain.request().url().uri().path.contains("access/vinculation")){
            requestBuilder.addHeader("Authorization", "Bearer ${retrofitRest.accessToken}")
        }
        val request = requestBuilder.build()

        var mainResponse =  chain.proceed(request)

        if (mainResponse.code() == 401){
            runBlocking {
                val response = retrofitRest.bind().await()

                if (response.isRight){
                    mainResponse.close()
                    mainResponse = chain.proceed(createNewRequest(response.getRight()!!.accessToken!!,chain))
                }else{
                    retrofitRest.accessToken = null
                    throw IOException("Unauthorized")

                }
            }

        }
        return mainResponse
    }

    private fun createNewRequest(accessToken : String,chain: Interceptor.Chain): Request {
        return chain.request()
            .newBuilder()
            .addHeader("X-API-KEY", retrofitRest.apiKey)
            .addHeader("Accept-Language", "es_ES")
            .addHeader("Authorization", "Bearer $accessToken").build()
    }
}