package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelationsLegacy

abstract class ICatalogRecipeDetailViewModel: BaseViewModel<ICatalogRecipeDetailViewModel.State>() {
    sealed class State {
        data class FetchRecipeSuccess(val catalogRecipeAgreggate: CatalogRecipeAgreggate): State()
        data class Error(val errorMessage: String): State()
        data class AddRecipeSuccess(val hasMarketIngredients: Boolean): State()
    }

    abstract fun fetchRecipe(recipeId: Int)
    abstract fun addRecipe(catalogRecipeAggregate: CatalogRecipeAgreggate)
}