package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.DoableRecipe

interface IGetDoableRecipeByIdAction: Action<IGetDoableRecipeByIdAction.Result> {
    sealed class Result {
        data class Success(val recipe: DoableRecipe): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetchRecipeById(id: Int)
}
