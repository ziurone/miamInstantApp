package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.DoableRecipe

interface IFetchRecipesWithIngredientsAction: Action<IFetchRecipesWithIngredientsAction.Result> {

    sealed class Result {
        data class Success(val doableRecipes: List<DoableRecipe>): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetch()
}