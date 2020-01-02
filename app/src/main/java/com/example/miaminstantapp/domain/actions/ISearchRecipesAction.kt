package com.example.miaminstantapp.domain.actions

interface ISearchRecipesAction: Action<ISearchRecipesAction.Result> {

    sealed class Result {
        object Success: Result()
        data class Error(val errorMessage: String): Result()
    }

    fun searchRecipes()
}