package com.example.miaminstantapp.viewmodel.recipebook

import com.example.miaminstantapp.domain.actions.recipebook.FetchRecipeBookIngredientsAction
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeIngredientEntity
import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class RecipeBookRecipeIngredientsViewModel @Inject constructor(
    private val fetchRecipeBookIngredientsAction: FetchRecipeBookIngredientsAction
) : BaseViewModel<RecipeBookRecipeIngredientsViewModel.State>() {

    sealed class State {
        data class FetchIngredientsSuccess(val ingredients: List<RecipeBookRecipeIngredientEntity>): State()
    }

    init {
        listenSource(fetchRecipeBookIngredientsAction.getLiveData(), ::onFetchIngredientsResult)
    }

    fun fetchIngredients(recipeId: Int) {
        fetchRecipeBookIngredientsAction.fetch(recipeId)
    }

    private fun onFetchIngredientsResult(result: FetchRecipeBookIngredientsAction.Result) {
        when(result) {
            is FetchRecipeBookIngredientsAction.Result.FetchIngredientsSuccess -> setState(State.FetchIngredientsSuccess(result.ingredients))
        }
    }

}