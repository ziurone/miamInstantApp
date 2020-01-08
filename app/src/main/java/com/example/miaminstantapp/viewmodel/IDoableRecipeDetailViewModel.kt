package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.entities.DoableRecipe

abstract class IDoableRecipeDetailViewModel: BaseViewModel<IDoableRecipeDetailViewModel.State>() {
    sealed class State {
        data class FetchRecipeSuccess(val doableRecipe: DoableRecipe): State()
        data class Error(val errorMessage: String): State()
    }

    abstract fun fetchRecipe(recipeId: Int)
}