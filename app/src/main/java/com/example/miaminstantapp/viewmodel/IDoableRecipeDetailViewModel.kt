package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.relations.CatalogRecipe

abstract class IDoableRecipeDetailViewModel: BaseViewModel<IDoableRecipeDetailViewModel.State>() {
    sealed class State {
        data class FetchRecipeSuccess(val catalogRecipe: CatalogRecipe): State()
        data class Error(val errorMessage: String): State()
        object AddRecipeSuccess: State()
    }

    abstract fun fetchRecipe(recipeId: Int)
    abstract fun addRecipe(catalogRecipe: CatalogRecipe)
}