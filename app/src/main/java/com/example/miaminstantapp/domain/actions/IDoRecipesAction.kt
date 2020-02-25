package com.example.miaminstantapp.domain.actions

interface IDoRecipesAction: Action<IDoRecipesAction.Result> {
    sealed class Result {
        object Success: Result()
        data class Error(val errorMessage: String)
    }

    fun doRecipes()
}