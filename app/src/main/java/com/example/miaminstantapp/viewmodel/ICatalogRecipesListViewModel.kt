package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.relations.DoableRecipe

abstract class ICatalogRecipesListViewModel: BaseViewModel<ICatalogRecipesListViewModel.State>() {
    sealed class State {
        data class FetchedRecipesSuccess(val doableRecipes: List<DoableRecipe>): State()
        data class Error(val errorMessage: String): State()
    }

    abstract fun fetchRecipes()
}