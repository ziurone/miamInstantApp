package com.example.miaminstantapp.routing

import android.content.Context
import com.example.miaminstantapp.SessionActivity
import com.example.miaminstantapp.view.SetUserFiltersActivity

class Launcher constructor(
    private val context: Context
) {

    fun filtersDislikeIngredients() {
        SetUserFiltersActivity.startActivity(context)
    }

    fun dispensary() {
        SessionActivity.startActivity(context)
    }
}