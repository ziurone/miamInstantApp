package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.IAddRecipeAction
import com.example.miaminstantapp.domain.actions.IGetCatalogRecipeByIdAction
import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelationsLegacy
import javax.inject.Inject

class CatalogRecipeDetailViewModel @Inject constructor(
    private val getCatalogRecipeByIdAction: IGetCatalogRecipeByIdAction,
    private val addRecipeAction: IAddRecipeAction
): ICatalogRecipeDetailViewModel() {

    init {
        listenSource(getCatalogRecipeByIdAction.getLiveData(), ::onGetCatalogRecipeByIdResult)
        listenSource(addRecipeAction.getLiveData(), ::onAddDoableRecipe)
    }

    override fun fetchRecipe(recipeId: Int) {
        getCatalogRecipeByIdAction.fetch(recipeId)
    }

    override fun addRecipe(catalogRecipeAggregate: CatalogRecipeAgreggate) {
        addRecipeAction.addRecipe(catalogRecipeAggregate)
    }

    private fun onAddDoableRecipe(result: IAddRecipeAction.Result) {
        when(result) {
            is IAddRecipeAction.Result.Success -> setState(State.AddRecipeSuccess(result.hasMarketIngredients))
            is IAddRecipeAction.Result.Error -> Unit
        }
    }

    private fun onGetCatalogRecipeByIdResult(result: IGetCatalogRecipeByIdAction.Result) {
        when(result) {
            is IGetCatalogRecipeByIdAction.Result.Success -> setState(State.FetchRecipeSuccess(result.recipeAggregate))
            is IGetCatalogRecipeByIdAction.Result.Error -> TODO()
        }
    }

}