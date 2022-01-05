package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelationsLegacy

interface IGetCatalogRecipeByIdAction: Action<IGetCatalogRecipeByIdAction.Result> {
    sealed class Result {
        data class Success(val recipeAggregate: CatalogRecipeAgreggate): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetch(id: Int)
}
