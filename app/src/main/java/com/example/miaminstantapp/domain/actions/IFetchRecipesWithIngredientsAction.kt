package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.CatalogRecipeRelations

interface IFetchRecipesWithIngredientsAction: Action<IFetchRecipesWithIngredientsAction.Result> {

    sealed class Result {
        data class Success(val catalogRecipeRelations: List<CatalogRecipeRelations>): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetch()
}