package com.example.miaminstantapp.view.recipedetail

import com.example.miaminstantapp.domain.actions.*
import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelationsLegacy
import com.example.miaminstantapp.domain.relations.MarketIngredientRelations
import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class CatalogRecipeDetailIngredientsListViewModel @Inject constructor(
    private val getCatalogRecipeByIdAction: GetCatalogRecipeByIdAction,
    private val addRecipeUserIngredientAction: AddRecipeUserIngredientAction,
    private val removeMarketIngredientAction: RemoveMarketIngredientAction
): BaseViewModel<CatalogRecipeDetailIngredientsListViewModel.State>() {

    init {
        listenSource(getCatalogRecipeByIdAction.getLiveData(), ::onGetCatalogRecipeByIdResult)
        listenSource(addRecipeUserIngredientAction.getLiveData(), ::onAddUserIngredientSuccess)
    }

    private lateinit var showedRecipeId: Number

    fun fetchRecipe(recipeId: Int) {
        showedRecipeId = recipeId
        getCatalogRecipeByIdAction.fetch(recipeId)
    }

    fun addUserIngredientFromMarketIngredient(marketIngredientRelations: MarketIngredientRelations) {
        //removeMarketIngredientAction.remove(marketIngredientRelations.marketIngredientLegacy)
//        addRecipeUserIngredientAction.addIngredient(marketIngredientRelations.toRecipeUserIngredient())
    }

    private fun onAddUserIngredientSuccess(result: AddRecipeUserIngredientAction.Result) {
        when(result) {
            AddRecipeUserIngredientAction.Result.Success -> {fetchRecipe(showedRecipeId.toInt())}
        }
    }

    private fun onGetCatalogRecipeByIdResult(result: IGetCatalogRecipeByIdAction.Result) {
        when(result) {
            is IGetCatalogRecipeByIdAction.Result.Success -> setState(State.FetchSuccess(result.recipeAggregate))
            is IGetCatalogRecipeByIdAction.Result.Error -> Unit
        }
    }

    sealed class State {
        data class FetchSuccess(val recipeAggregate: CatalogRecipeAgreggate): State()
        object RefetchSuccess: State()
    }

}