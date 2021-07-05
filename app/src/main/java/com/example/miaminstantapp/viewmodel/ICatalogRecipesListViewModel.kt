package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.relations.CatalogRecipeRelations

abstract class ICatalogRecipesListViewModel: BaseViewModel<ICatalogRecipesListViewModel.State>() {
    sealed class State {
        data class FetchedRecipesSuccess(val catalogRecipeRelations: List<CatalogRecipeRelations>): State()
        data class Error(val errorMessage: String): State()
    }

    abstract fun fetchRecipes()
}