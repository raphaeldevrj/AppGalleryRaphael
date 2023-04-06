package com.example.appgallery_raphael.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    val client = OkHttpClient
        .Builder()
        .addInterceptor { chain ->

            val originalRequest = chain.request()
            val originalUrl = originalRequest.url()
            val newUrl = originalUrl.newBuilder()
                .build()

            val newRequest = originalRequest.newBuilder().url(newUrl).build()

            chain.proceed(
                newRequest
                    .newBuilder()
                    .addHeader("Authorization", "Client-ID ba083072975c41c")
                    .build()
            )
        }

        .build()


    private val retrofit by lazy {
        val gson = GsonBuilder().serializeNulls().create()
        Retrofit.Builder()
            .baseUrl(" https://api.imgur.com/3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    val apiImgur: ImgurApi by lazy {
        retrofit.create(ImgurApi::class.java)
    }
}


