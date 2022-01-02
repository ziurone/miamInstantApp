package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.CatalogRecipeRelationsLegacy

interface IAddRecipeAction: Action<IAddRecipeAction.Result> {
    sealed class Result {
        object Success: Result()
        data class Error(val errorMessage: String): Result()
    }

    fun addRecipe(recipeLegacy: CatalogRecipeRelationsLegacy)
}