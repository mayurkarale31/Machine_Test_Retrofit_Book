package com.bitcodetech.book_machine_test.commons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.book_machine_test.repository.BookRepository
import com.bitcodetech.book_machine_test.viewmodels.BookViewModel

class BookViewModelFactory(
    private val bookRepository: BookRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookViewModel(bookRepository) as T
    }
}