package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.FetchRecipesWithIngredientsAction
import com.example.miaminstantapp.domain.actions.IFetchRecipesWithIngredientsAction
import javax.inject.Inject

class CatalogRecipesListViewModel @Inject constructor(
    private val fetchRecipesWithIngredientsAction: FetchRecipesWithIngredientsAction
): ICatalogRecipesListViewModel() {

    init {
        listenSource(fetchRecipesWithIngredientsAction.getLiveData(), ::onFetchRecipesResult)
    }

    override fun fetchRecipes() {
        fetchRecipesWithIngredientsAction.fetch()
    }

    private fun onFetchRecipesResult(result: IFetchRecipesWithIngredientsAction.Result) {
        when(result) {
            is IFetchRecipesWithIngredientsAction.Result.Success -> setState(State.FetchedRecipesSuccess(result.catalogRecipeRelations))
            is IFetchRecipesWithIngredientsAction.Result.Error -> Unit
        }
    }
}