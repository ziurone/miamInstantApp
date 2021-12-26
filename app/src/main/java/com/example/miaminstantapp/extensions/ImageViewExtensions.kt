package com.example.miaminstantapp.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.example.miaminstantapp.R

fun ImageView.loadImageURL(url: String, @DrawableRes placeHolder : Int  = R.drawable.ic_recipe_card_empty_image, @DrawableRes error : Int  = R.drawable.ic_recipe_card_empty_image) {
    Glide.with(this.context)
        .load(url)
        .placeholder(placeHolder)
        .fitCenter()
        .error(error)
        .into(this)
}