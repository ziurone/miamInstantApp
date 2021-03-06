package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelationsLegacy

interface IAddRecipeAction: Action<IAddRecipeAction.Result> {
    sealed class Result {
        data class Success(val hasMarketIngredients: Boolean): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun addRecipe(catalogRecipeAggregate: CatalogRecipeAgreggate)
}