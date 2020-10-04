package com.example.miaminstantapp.routing

import android.content.Context
import com.example.miaminstantapp.MainActivity
import com.example.miaminstantapp.view.NavigationActivity
import com.example.miaminstantapp.view.SetUserFiltersActivity

class Launcher constructor(
    private val context: Context
) {

    fun filtersDislikeIngredients() {
        SetUserFiltersActivity.startActivity(context)
    }

    fun dispensary() {
        MainActivity.startActivity(context)
    }
}