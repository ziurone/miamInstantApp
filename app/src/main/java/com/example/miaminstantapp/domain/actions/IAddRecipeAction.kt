package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.CatalogRecipeRelations

interface IAddRecipeAction: Action<IAddRecipeAction.Result> {
    sealed class Result {
        object Success: Result()
        data class Error(val errorMessage: String): Result()
    }

    fun addRecipe(recipe: CatalogRecipeRelations)
}