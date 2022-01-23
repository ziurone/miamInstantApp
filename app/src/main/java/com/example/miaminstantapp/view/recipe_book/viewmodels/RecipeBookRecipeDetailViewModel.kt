package com.example.miaminstantapp.view.recipe_book.viewmodels

import com.example.miaminstantapp.domain.actions.FetchRecipeBookRecipeAction
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class RecipeBookRecipeDetailViewModel @Inject constructor(
    private val fetchRecipeBookRecipesAction: FetchRecipeBookRecipeAction
): BaseViewModel<RecipeBookRecipeDetailViewModel.State>() {

    sealed class State {
        data class FetchRecipeSuccess(val recipe: RecipeBookRecipeEntity): State()
    }

    init {
        listenSource(fetchRecipeBookRecipesAction.getLiveData(), ::onResult)
    }

    fun fetchRecipe(recipeId: Int) {
        fetchRecipeBookRecipesAction.fetch(recipeId)
    }

    private fun onResult(result: FetchRecipeBookRecipeAction.Result) {
        when(result) {
            is FetchRecipeBookRecipeAction.Result.FetchRecipeSuccess -> setState(State.FetchRecipeSuccess(result.recipe))
        }
    }

}