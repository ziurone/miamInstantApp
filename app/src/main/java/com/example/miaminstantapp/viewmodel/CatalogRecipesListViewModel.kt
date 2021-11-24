package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.FetchRecipesWithIngredientsAction
import com.example.miaminstantapp.domain.actions.IFetchRecipesWithIngredientsAction
import com.example.miaminstantapp.domain.actions.IFetchSuggestedIngredientsAction
import com.example.miaminstantapp.domain.actions.suggestedIngredients.RemoveSuggestedIngredientAction
import com.example.miaminstantapp.domain.dtos.Ingredient
import javax.inject.Inject

class CatalogRecipesListViewModel @Inject constructor(
    private val fetchRecipesWithIngredientsAction: FetchRecipesWithIngredientsAction,
    private val fetchSuggestedIngredientsAction: IFetchSuggestedIngredientsAction,
    private val removeSuggestedIngredientAction: RemoveSuggestedIngredientAction
): ICatalogRecipesListViewModel() {

    private var replaceSuggestedIngredientsQuantity = 0

    init {
        listenSource(fetchRecipesWithIngredientsAction.getLiveData(), ::onFetchRecipesResult)
        listenSource(fetchSuggestedIngredientsAction.getLiveData(), ::onFetchSuggestedIngredientsResult)
        listenSource(removeSuggestedIngredientAction.getLiveData(), ::onRemoveSuggestedIngredientResult)
    }

    override fun fetchRecipes() {
        fetchRecipesWithIngredientsAction.fetch()
    }

    override fun addIngredient(ingredient: Ingredient) {
        replaceSuggestedIngredientsQuantity =+ 1
//        suggestedIngredientsShowedIds.add(ingredient.id)
//        addUserIngredientAction.add(ingredient)
    }

    override fun removeSuggestedIngredient(ingredient: Ingredient) {
        removeSuggestedIngredientAction.remove(ingredient)
    }

    override fun fetchSuggestedIngredients() {
        fetchSuggestedIngredientsAction.fetch()
    }

    private fun onRemoveSuggestedIngredientResult(result: RemoveSuggestedIngredientAction.Result) {
        when(result) {
            RemoveSuggestedIngredientAction.Result.Success -> fetchSuggestedIngredients()
            RemoveSuggestedIngredientAction.Result.Error -> Unit
        }
    }

    private fun onFetchSuggestedIngredientsResult(result: IFetchSuggestedIngredientsAction.Result) {
        when (result) {
            is IFetchSuggestedIngredientsAction.Result.Error -> Unit
            is IFetchSuggestedIngredientsAction.Result.Success -> onFetchSuggestedIngredientsSuccess(result.data.map { it.toIngredient() })
        }
    }

    private fun onFetchSuggestedIngredientsSuccess(ingredients: List<Ingredient>) {
        setState(State.FetchSuggestedIngredientsSuccess(ingredients))
    }

    private fun onFetchRecipesResult(result: IFetchRecipesWithIngredientsAction.Result) {
        when(result) {
            is IFetchRecipesWithIngredientsAction.Result.Success -> setState(State.FetchedRecipesSuccess(result.catalogRecipeRelations))
            is IFetchRecipesWithIngredientsAction.Result.Error -> Unit
        }
    }
}