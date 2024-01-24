package com.bitcodetech.book_machine_test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.book_machine_test.R
import com.bitcodetech.book_machine_test.databinding.BookViewBinding
import com.bitcodetech.book_machine_test.models.Book
import com.bumptech.glide.Glide

class BookAdapter(
    private val books : ArrayList<Book>
): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private lateinit var binding: BookViewBinding

    interface OnBookImageListener{
        fun onBookCLick(book: Book, position: Int, bookAdapter: BookAdapter)
    }
    var onBookImageListener : OnBookImageListener? = null

    inner class BookViewHolder(view: View):RecyclerView.ViewHolder(view){

        val binding : BookViewBinding

        init {
            binding = BookViewBinding.bind(view)
            binding.root.setOnClickListener {
                onBookImageListener?.onBookCLick(
                    books[adapterPosition],
                    adapterPosition,
                    this@BookAdapter
                )

            }
        }
    }

    override fun getItemCount() = books.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_view,null)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        //holder.txtTitle.text = book.title
        holder.binding.txtTitle.text = book.title
        //  holder.binding.txtSubtitle.text = book.subtitle

        Glide.with(holder.itemView)
            .load(books[position].image)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .circleCrop()
            .into(holder.binding.imgBooks)
    }
}