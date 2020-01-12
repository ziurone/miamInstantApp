package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.DoableRecipe

interface IAddRecipeAction: Action<IAddRecipeAction.Result> {
    sealed class Result {
        object Success: Result()
        data class Error(val errorMessage: String): Result()
    }

    fun addRecipe(recipe: DoableRecipe)
}