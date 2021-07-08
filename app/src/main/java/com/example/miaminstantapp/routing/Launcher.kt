package com.example.miaminstantapp.routing

import android.content.Context
import com.example.miaminstantapp.SessionActivity
import com.example.miaminstantapp.view.SetUserFiltersActivity
import com.example.miaminstantapp.view.recipedetail.CatalogRecipeDetailActivity

class Launcher constructor(
    private val context: Context
) {

    fun filtersDislikeIngredients() {
        SetUserFiltersActivity.startActivity(context)
    }

    fun dispensary() {
        SessionActivity.startActivity(context)
    }

    fun catalogRecipeDetail(recipeId: Int) {
        CatalogRecipeDetailActivity.startActivity(context, recipeId)
    }
}