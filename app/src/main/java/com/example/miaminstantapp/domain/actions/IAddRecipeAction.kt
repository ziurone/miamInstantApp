package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.DoableRecipe
import com.example.miaminstantapp.domain.entities.MarketRecipeEntity
import com.example.miaminstantapp.domain.entities.RecipeUserIngredientEntity
import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import io.reactivex.Completable

interface IAddRecipeAction: Action<IAddRecipeAction.Result> {
    sealed class Result {
        object Success: Result()
        data class Error(val errorMessage: String): Result()
    }

    fun addRecipe(recipe: DoableRecipe)
}