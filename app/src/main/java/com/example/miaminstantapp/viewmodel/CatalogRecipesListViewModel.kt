package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.FetchRecipesWithIngredientsAction
import com.example.miaminstantapp.domain.actions.IFetchRecipesWithIngredientsAction
import com.example.miaminstantapp.domain.actions.IFetchSuggestedIngredientsAction
import com.example.miaminstantapp.domain.dtos.Ingredient
import javax.inject.Inject

class CatalogRecipesListViewModel @Inject constructor(
    private val fetchRecipesWithIngredientsAction: FetchRecipesWithIngredientsAction,
    private val fetchSuggestedIngredientsAction: IFetchSuggestedIngredientsAction
): ICatalogRecipesListViewModel() {

    private var replaceSuggestedIngredientsQuantity = 0
    private val suggestedIngredientsShowedIds = mutableListOf<Int>()

    init {
        listenSource(fetchRecipesWithIngredientsAction.getLiveData(), ::onFetchRecipesResult)
        listenSource(fetchSuggestedIngredientsAction.getLiveData(), ::onFetchSuggestedIngredientsResult)
    }

    override fun fetchRecipes() {
        fetchRecipesWithIngredientsAction.fetch()
    }

    override fun addIngredient(ingredient: Ingredient) {
        replaceSuggestedIngredientsQuantity =+ 1
        suggestedIngredientsShowedIds.add(ingredient.id)
//        addUserIngredientAction.add(ingredient)
    }

    override fun fetchSuggestedIngredients() {
        fetchSuggestedIngredientsAction.fetch(suggestedIngredientsShowedIds)
    }

    private fun onFetchSuggestedIngredientsResult(result: IFetchSuggestedIngredientsAction.Result) {
        when (result) {
            is IFetchSuggestedIngredientsAction.Result.Error -> Unit
            is IFetchSuggestedIngredientsAction.Result.Success -> onFetchSuggestedIngredientsSuccess(result.data.ingredients)
        }
    }

    private fun onFetchSuggestedIngredientsSuccess(ingredients: List<Ingredient>) {
        setState(State.FetchSuggestedIngredientsSuccess(ingredients))
        suggestedIngredientsShowedIds.addAll(ingredients.map { it.id })
    }

    private fun onFetchRecipesResult(result: IFetchRecipesWithIngredientsAction.Result) {
        when(result) {
            is IFetchRecipesWithIngredientsAction.Result.Success -> setState(State.FetchedRecipesSuccess(result.catalogRecipeRelations))
            is IFetchRecipesWithIngredientsAction.Result.Error -> Unit
        }
    }
}