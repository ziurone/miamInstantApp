package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.IAddRecipeAction
import com.example.miaminstantapp.domain.actions.IGetDoableRecipeByIdAction
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelationsLegacy
import javax.inject.Inject

class CatalogRecipeDetailViewModel @Inject constructor(
    private val getDoableRecipeByIdAction: IGetDoableRecipeByIdAction,
    private val addRecipeAction: IAddRecipeAction
): ICatalogRecipeDetailViewModel() {

    init {
        listenSource(getDoableRecipeByIdAction.getLiveData(), ::onGetDoableRecipeByIdResult)
        listenSource(addRecipeAction.getLiveData(), ::onAddDoableRecipe)
    }

    override fun fetchRecipe(recipeId: Int) {
        getDoableRecipeByIdAction.fetchRecipeById(recipeId)
    }

    override fun addRecipe(catalogRecipeRelationsLegacy: CatalogRecipeRelationsLegacy) {
        addRecipeAction.addRecipe(catalogRecipeRelationsLegacy)
    }

    private fun onAddDoableRecipe(result: IAddRecipeAction.Result) {
        when(result) {
            is IAddRecipeAction.Result.Success -> setState(State.AddRecipeSuccess)
            is IAddRecipeAction.Result.Error -> Unit
        }
    }

    private fun onGetDoableRecipeByIdResult(result: IGetDoableRecipeByIdAction.Result) {
        when(result) {
            is IGetDoableRecipeByIdAction.Result.Success -> setState(State.FetchRecipeSuccess(result.recipeLegacy))
        }
    }

}