package com.bitcodetech.book_machine_test.commons

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image_url")
fun setImageUrlToImageView(imageView: ImageView, imageUrl : String){
    Glide.with(imageView)
        .load(imageUrl)
        .into(imageView)
}