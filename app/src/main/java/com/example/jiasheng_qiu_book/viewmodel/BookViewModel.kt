package com.example.jiasheng_qiu_book.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiasheng_qiu_book.network.BookItem
import com.example.jiasheng_qiu_book.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {
    private val _books = MutableStateFlow<List<BookItem>>(emptyList())
    val books: StateFlow<List<BookItem>> get() = _books

    fun searchBooks(query: String) {
        viewModelScope.launch {
            repository.searchBooks(query).collect { response ->
                _books.value = response.items ?: emptyList()
            }
        }
    }

    fun getBookDetails(volumeId: String, onResult: (BookItem?) -> Unit) {
        viewModelScope.launch {
            try {
                // Fetch book details using the volume ID
                val bookItem = repository.getBookDetailsByVolumeId(volumeId)

                onResult(bookItem) // Pass the result back to the UI
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(null) // Handle errors by passing a null result
            }
        }
    }
}
