package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria

interface ISearchRecipesAction: Action<ISearchRecipesAction.Result> {

    sealed class Result {
        object Success: Result()
        data class Error(val errorMessage: String): Result()
    }

    fun searchRecipes(searchCriteria: RecipeSearchCriteria)
}