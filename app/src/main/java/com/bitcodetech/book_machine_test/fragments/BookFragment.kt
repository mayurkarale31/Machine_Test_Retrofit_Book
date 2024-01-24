package com.bitcodetech.book_machine_test.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.book_machine_test.R
import com.bitcodetech.book_machine_test.adapter.BookAdapter
import com.bitcodetech.book_machine_test.commons.BookViewModelFactory
import com.bitcodetech.book_machine_test.databinding.BookFragmentBinding
import com.bitcodetech.book_machine_test.models.Book
import com.bitcodetech.book_machine_test.network.BookApiService
import com.bitcodetech.book_machine_test.repository.BookRepository
import com.bitcodetech.book_machine_test.viewmodels.BookViewModel

class BookFragment : Fragment() {
    private lateinit var binding : BookFragmentBinding
    private lateinit var bookViewModel : BookViewModel
    private lateinit var bookAdapter : BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BookFragmentBinding.inflate(layoutInflater)

        initViews()
        initViewModels()
        initAdapter()
        initObserver()
        initListeners()

        bookViewModel.fetchBooks()

        return binding.root
    }

    private fun initListeners(){
        binding.recyclerBook.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        bookViewModel.fetchBooks()
                    }
                }
            })

        bookAdapter.onBookImageListener =
            object  : BookAdapter.OnBookImageListener{
                override fun onBookCLick(book: Book, position: Int, bookAdapter: BookAdapter) {
                    showDetailsFragment(book)
                }
            }
    }

    private fun showDetailsFragment(book : Book){
        val bookDetailsFragment = BookDetailsFragment()

        val bundles = Bundle()
        bundles.putSerializable("books", book)

        bookDetailsFragment.arguments = bundles

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer,bookDetailsFragment,null)
            .addToBackStack(null)
            .commit()
    }

    private fun initAdapter(){
        bookAdapter = BookAdapter(bookViewModel.books)
        binding.recyclerBook.adapter = bookAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver(){
        bookViewModel.bookMutableLiveData.observe(
            viewLifecycleOwner
        ){
            if (it){
                bookAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initViewModels(){
        bookViewModel = ViewModelProvider(
            this,
            BookViewModelFactory(
                BookRepository(
                    BookApiService.getInstance()
                )
            )
        )[BookViewModel::class.java]
    }

    private fun initViews(){
        binding.recyclerBook.layoutManager=
            LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,
                false)
    }
}