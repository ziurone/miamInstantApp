package com.example.miaminstantapp.view.recipe_book.viewmodels

import com.example.miaminstantapp.domain.actions.recipebook.FetchRecipeBookRecipeAction
import com.example.miaminstantapp.domain.actions.recipebook.SetRecipeAsMadeAction
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class RecipeBookRecipeDetailViewModel @Inject constructor(
    private val fetchRecipeBookRecipesAction: FetchRecipeBookRecipeAction,
    private val setRecipeAsMadeAction: SetRecipeAsMadeAction
): BaseViewModel<RecipeBookRecipeDetailViewModel.State>() {

    sealed class State {
        data class FetchRecipeSuccess(val recipe: RecipeBookRecipeEntity): State()
    }

    init {
        listenSource(fetchRecipeBookRecipesAction.getLiveData(), ::onResult)
        listenSource(setRecipeAsMadeAction.getLiveData(), ::onSetRecipeAsMadeResult)
    }

    fun fetchRecipe(recipeId: Int) {
        fetchRecipeBookRecipesAction.fetch(recipeId)
    }

    fun setRecipeAsMade(recipeId: Int) {
        setRecipeAsMadeAction.set(recipeId)
    }

    private fun onSetRecipeAsMadeResult(result: SetRecipeAsMadeAction.Result) {
        when(result) {
            SetRecipeAsMadeAction.Result.RecipeSetAsMadeSuccess -> Unit
        }
    }

    private fun onResult(result: FetchRecipeBookRecipeAction.Result) {
        when(result) {
            is FetchRecipeBookRecipeAction.Result.FetchRecipeSuccess -> setState(State.FetchRecipeSuccess(result.recipe))
        }
    }

}