package com.example.miaminstantapp.view.recipedetail

import com.example.miaminstantapp.domain.actions.GetCatalogRecipeByIdAction
import com.example.miaminstantapp.domain.actions.IGetDoableRecipeByIdAction
import com.example.miaminstantapp.domain.relations.CatalogRecipe
import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class CatalogRecipeDetailIngredientsListViewModel @Inject constructor(
    private val getCatalogRecipeByIdAction: GetCatalogRecipeByIdAction
): BaseViewModel<CatalogRecipeDetailIngredientsListViewModel.State>() {

    init {
        listenSource(getCatalogRecipeByIdAction.getLiveData(), ::onGetCatalogRecipeByIdResult)
    }

    fun fetchRecipe(recipeId: Int) {
        getCatalogRecipeByIdAction.fetchRecipeById(recipeId)
    }

    private fun onGetCatalogRecipeByIdResult(result: IGetDoableRecipeByIdAction.Result) {
        when(result) {
            is IGetDoableRecipeByIdAction.Result.Success -> setState(State.FetchSuccess(result.recipe))
            is IGetDoableRecipeByIdAction.Result.Error -> Unit
        }
    }

    sealed class State {
        data class FetchSuccess(val recipe: CatalogRecipe): State()
    }

}