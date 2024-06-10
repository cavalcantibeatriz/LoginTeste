package com.example.mobilefaztudo.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    //Insira seu ip fixo aqui e mude também no arquivo res/xml/network_security_config.xml
    // 54.88.187.162
    private const val BASE_URL = "http://192.168.0.122:8080/"
    private const val CONNECT_TIMEOUT = 30
    private const val CONTENT_TYPE_JSON = "application/json"

    fun getInstance(): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", CONTENT_TYPE_JSON)
                    .build()
                chain.proceed(request)
            })
            .build()

        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }
}