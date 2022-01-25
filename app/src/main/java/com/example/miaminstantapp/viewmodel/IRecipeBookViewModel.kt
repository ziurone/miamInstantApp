package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity

abstract class IRecipeBookViewModel: BaseViewModel<IRecipeBookViewModel.State>() {
    sealed class State {
        data class FetchRecipeSuccess(val recipes: List<RecipeBookRecipeEntity>): State()
        data class Error(val errorMessage: String): State()
    }

    abstract fun fetchRecipes(made: Boolean)
}