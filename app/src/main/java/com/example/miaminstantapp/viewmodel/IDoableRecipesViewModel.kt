package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.entities.RecipeWithUserIngredients

abstract class IDoableRecipesViewModel: BaseViewModel<IDoableRecipesViewModel.State>() {
    sealed class State {
        data class FetchedRecipesSuccess(val recipes: List<RecipeWithUserIngredients>): State()
        data class Error(val errorMessage: String): State()
    }

    abstract fun fetchRecipes()
}