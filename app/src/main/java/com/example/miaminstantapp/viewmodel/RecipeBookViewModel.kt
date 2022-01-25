package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.IFetchRecipeBookRecipesAction
import javax.inject.Inject

class RecipeBookViewModel @Inject constructor(
    private val fetchRecipeBookRecipesAction: IFetchRecipeBookRecipesAction
): IRecipeBookViewModel() {
    init {
        listenSource(fetchRecipeBookRecipesAction.getLiveData(), ::onFetchRecipesResult)
    }

    override fun fetchRecipes(made: Boolean) = fetchRecipeBookRecipesAction.fetch(made)

    private fun onFetchRecipesResult(result: IFetchRecipeBookRecipesAction.Result) {
        when(result) {
            is IFetchRecipeBookRecipesAction.Result.Success -> setState(State.FetchRecipeSuccess(result.recipes))
            is IFetchRecipeBookRecipesAction.Result.Error -> TODO()
        }
    }
}