package com.example.jiasheng_qiu_book.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"
    const val API_KEY = "AIzaSyDZ1oUDPgc9egccV1ZmmLcgKhLA9CzoIaM"

    val api: BookApiService by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()) // Add this line to support Kotlin serialization
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)) // Use the configured Moshi instance
            .build()
            .create(BookApiService::class.java)
    }
}