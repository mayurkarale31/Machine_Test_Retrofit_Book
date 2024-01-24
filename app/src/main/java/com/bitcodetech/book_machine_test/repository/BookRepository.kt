package com.bitcodetech.book_machine_test.repository

import com.bitcodetech.book_machine_test.models.Book
import com.bitcodetech.book_machine_test.network.BookApiService

class BookRepository(
    private val bookApiService : BookApiService
) {
    suspend fun fetchBooks() : ArrayList<Book>{
        return bookApiService.fetchBooks().books
    }
}