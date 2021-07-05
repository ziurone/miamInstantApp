package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.relations.CatalogRecipeRelations

abstract class ICatalogRecipeDetailViewModel: BaseViewModel<ICatalogRecipeDetailViewModel.State>() {
    sealed class State {
        data class FetchRecipeSuccess(val catalogRecipeRelations: CatalogRecipeRelations): State()
        data class Error(val errorMessage: String): State()
        object AddRecipeSuccess: State()
    }

    abstract fun fetchRecipe(recipeId: Int)
    abstract fun addRecipe(catalogRecipeRelations: CatalogRecipeRelations)
}