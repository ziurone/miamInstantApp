package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.RecipeWithUserIngredients

interface IFetchRecipesWithIngredientsAction: Action<IFetchRecipesWithIngredientsAction.Result> {

    sealed class Result {
        data class Success(val recipes: List<RecipeWithUserIngredients>): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetch()
}