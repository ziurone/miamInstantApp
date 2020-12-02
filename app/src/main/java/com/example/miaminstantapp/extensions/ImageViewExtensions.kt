package com.example.miaminstantapp.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.example.miaminstantapp.R

fun ImageView.loadImageURL(url: String, @DrawableRes placeHolder : Int  = R.drawable.placeholder_recipe_list_image, @DrawableRes error : Int  = R.drawable.placeholder_recipe_list_image) {
    Glide.with(this.context)
        .load(url)
        .placeholder(placeHolder)
        .fitCenter()
        .error(error)
        .into(this)
}