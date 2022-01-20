package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity

interface IFetchRecipeBookRecipesAction: Action<IFetchRecipeBookRecipesAction.Result> {
    sealed class Result {
        data class Success(val recipes: List<RecipeBookRecipeEntity>): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetch()
}