package com.bitcodetech.book_machine_test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitcodetech.book_machine_test.databinding.BookDetailsFragmentBinding
import com.bitcodetech.book_machine_test.databinding.BookViewBinding
import com.bitcodetech.book_machine_test.models.Book

class BookDetailsFragment : Fragment() {

    private lateinit var binding: BookDetailsFragmentBinding
    private var book : Book? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BookDetailsFragmentBinding.inflate(layoutInflater)

        binding.root.setOnClickListener {  }

        if(arguments != null) {
            book = requireArguments().getSerializable("books") as Book
            binding.book = book
        }
        return binding.root
    }
}