package com.example.jiasheng_qiu_book.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jiasheng_qiu_book.repository.BookRepository

class BookViewModelFactory(
    private val repository: BookRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookViewModel(repository) as T
    }
}