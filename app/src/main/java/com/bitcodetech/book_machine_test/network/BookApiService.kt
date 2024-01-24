package com.bitcodetech.book_machine_test.network

import com.bitcodetech.book_machine_test.models.BookResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BookApiService {

    @GET("new")
    suspend fun fetchBooks() : BookResponse

    companion object{
        private var bookApiService : BookApiService? = null

        fun getInstance() : BookApiService{
            if (bookApiService == null){
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client =  OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.itbook.store/1.0/")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                        bookApiService = retrofit.create(BookApiService::class.java)
            }
            return bookApiService!!
        }

    }
}