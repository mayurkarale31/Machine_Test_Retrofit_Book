package com.bitcodetech.book_machine_test.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.book_machine_test.models.Book
import com.bitcodetech.book_machine_test.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookViewModel(
    private val bookRepository : BookRepository
): ViewModel() {

    val bookMutableLiveData = MutableLiveData<Boolean>()
    val books = ArrayList<Book>()

    fun fetchBooks(){
        CoroutineScope(Dispatchers.IO).launch {
            val books = bookRepository.fetchBooks()

            withContext(Dispatchers.Main){
                this@BookViewModel.books.addAll(books)
                bookMutableLiveData.postValue(true)
            }
        }
    }
}