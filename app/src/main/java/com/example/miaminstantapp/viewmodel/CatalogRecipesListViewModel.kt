package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.*
import com.example.miaminstantapp.domain.actions.suggestedIngredients.RemoveSuggestedIngredientAction
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import javax.inject.Inject

class CatalogRecipesListViewModel @Inject constructor(
    private val fetchRecipesWithIngredientsAction: FetchRecipesWithIngredientsAction,
    private val fetchSuggestedIngredientsAction: IFetchSuggestedIngredientsAction,
    private val removeSuggestedIngredientAction: RemoveSuggestedIngredientAction,
    private val addUserIngredientAction: AddUserIngredientAction,
    private val searchRecipesAction: ISearchRecipesAction
): ICatalogRecipesListViewModel() {

    init {
        listenSource(fetchRecipesWithIngredientsAction.getLiveData(), ::onFetchRecipesResult)
        listenSource(fetchSuggestedIngredientsAction.getLiveData(), ::onFetchSuggestedIngredientsResult)
        listenSource(removeSuggestedIngredientAction.getLiveData(), ::onRemoveSuggestedIngredientResult)
        listenSource(addUserIngredientAction.getLiveData(), ::onAddedIngredientResult)
        listenSource(searchRecipesAction.getLiveData(), ::onSearchedRecipes)
    }

    override fun searchRecipes() {
        searchRecipesAction.searchRecipes(RecipeSearchCriteria(listOf(), 0, listOf(), listOf()))
    }

    override fun fetchRecipes() {
        fetchRecipesWithIngredientsAction.fetch()
    }

    override fun addIngredient(ingredient: Ingredient) {
        addUserIngredientAction.add(ingredient)
    }

    override fun removeSuggestedIngredient(ingredient: Ingredient) {
        removeSuggestedIngredientAction.remove(ingredient)
    }

    override fun fetchSuggestedIngredients() {
        fetchSuggestedIngredientsAction.fetch()
    }

    private fun onSearchedRecipes(result: ISearchRecipesAction.Result) {
        when(result) {
            is ISearchRecipesAction.Result.Error -> throw Exception(result.errorMessage)
            ISearchRecipesAction.Result.Success -> {
                fetchRecipes()
            }
        }
    }

    private fun onAddedIngredientResult(result: IAddUserIngredientAction.Result) {
        when(result) {
            is IAddUserIngredientAction.Result.Error -> throw Exception()
            IAddUserIngredientAction.Result.Success -> Unit
        }
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
            is IFetchRecipesWithIngredientsAction.Result.Success -> setState(State.FetchedRecipesSuccess(result.catalogRecipesAgreggates))
            is IFetchRecipesWithIngredientsAction.Result.Error -> Unit
        }
    }
}