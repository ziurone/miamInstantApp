package com.example.miaminstantapp.view.recipe_book.viewmodels

import com.example.miaminstantapp.domain.actions.IFetchRecipeBookRecipesAction
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class RecipeBookToDoRecipesViewModel @Inject constructor(
    private val fetchRecipeBookRecipesAction: IFetchRecipeBookRecipesAction
): BaseViewModel<RecipeBookToDoRecipesViewModel.State>() {

    sealed class State {
        object Loading: State()
        data class FetchRecipesSuccess(val recipes: List<RecipeBookRecipeEntity>) : State()
        data class Error(val message: String) : State()
    }

    init {
        listenSource(fetchRecipeBookRecipesAction.getLiveData(), ::onFetchRecipeBookRecipes)
    }

    fun fetchRecipes(made: Boolean) {
        fetchRecipeBookRecipesAction.fetch(made)
    }

    private fun onFetchRecipeBookRecipes(result: IFetchRecipeBookRecipesAction.Result) {
        when(result) {
            is IFetchRecipeBookRecipesAction.Result.Success -> setState(State.FetchRecipesSuccess(result.recipes))
            is IFetchRecipeBookRecipesAction.Result.Error -> TODO()
        }
    }
}