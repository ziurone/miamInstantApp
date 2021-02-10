package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.relations.CatalogRecipe

abstract class ICatalogRecipesListViewModel: BaseViewModel<ICatalogRecipesListViewModel.State>() {
    sealed class State {
        data class FetchedRecipesSuccess(val catalogRecipes: List<CatalogRecipe>): State()
        data class Error(val errorMessage: String): State()
    }

    abstract fun fetchRecipes()
}