package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelationsLegacy

interface IFetchRecipesWithIngredientsAction: Action<IFetchRecipesWithIngredientsAction.Result> {

    sealed class Result {
        data class Success(val catalogRecipesAgreggates: List<CatalogRecipeAgreggate>): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetch()
}