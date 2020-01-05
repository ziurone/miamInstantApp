package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.entities.RecipeWithUserIngredients

abstract class IDoableRecipeDetailViewModel: BaseViewModel<IDoableRecipeDetailViewModel.State>() {
    sealed class State {
        data class FetchRecipeSuccess(val recipe: RecipeWithUserIngredients): State()
        data class Error(val errorMessage: String): State()
    }

    abstract fun fetchRecipe(recipeId: Int)
}