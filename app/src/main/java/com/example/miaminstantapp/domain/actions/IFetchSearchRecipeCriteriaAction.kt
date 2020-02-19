package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria

interface IFetchSearchRecipeCriteriaAction: Action<IFetchSearchRecipeCriteriaAction.Result> {
    sealed class Result {
        data class Success(val recipeSearchCriteria: RecipeSearchCriteria): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetch()
}