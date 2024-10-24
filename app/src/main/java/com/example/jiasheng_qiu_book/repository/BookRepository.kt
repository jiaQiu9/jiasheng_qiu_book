package com.example.jiasheng_qiu_book.repository

import com.example.jiasheng_qiu_book.network.BookApiService
import com.example.jiasheng_qiu_book.network.BookItem
import com.example.jiasheng_qiu_book.network.BookResponse
import com.example.jiasheng_qiu_book.network.RetrofitInstance.api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRepository(private val apiService: BookApiService) {
    fun searchBooks(query: String): Flow<BookResponse> = flow {
        emit(apiService.searchBooks(query))
    }
    suspend fun getBookDetailsByVolumeId(volumeId: String): BookItem {
        return apiService.getBookDetailsByVolumeId(volumeId)
    }

}