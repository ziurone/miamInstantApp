package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.CatalogRecipe

interface IFetchRecipesWithIngredientsAction: Action<IFetchRecipesWithIngredientsAction.Result> {

    sealed class Result {
        data class Success(val catalogRecipes: List<CatalogRecipe>): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetch()
}