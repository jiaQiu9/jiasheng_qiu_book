package com.example.jiasheng_qiu_book.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String = RetrofitInstance.API_KEY
    ): BookResponse

    @GET("volumes/{volumeId}?") // Adjusted endpoint for fetching book details
    suspend fun getBookDetailsByVolumeId(
        @Path("volumeId") volumeId: String,
        @Query("key") apiKey: String = RetrofitInstance.API_KEY
    ): BookItem
}