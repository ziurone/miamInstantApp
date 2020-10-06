package com.example.miaminstantapp.viewmodel

import android.util.Log
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import com.example.miaminstantapp.domain.actions.*
import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import javax.inject.Inject

class UserIngredientsViewModel @Inject constructor(
    private val fetchSuggestedIngredientsAction: IFetchSuggestedIngredientsAction,
    private val fetchVolumeUnitsAction: IFetchVolumeUnitsAction,
    private val addVolumeUnitsAction: IAddVolumeUnitsAction,
    private val addUserIngredientAction: IAddUserIngredientAction,
    private val fetchUserIngredientsAction: IFetchUserIngredientsAction,
    private val getIngredientsByNameAction: IGetIngredientsByNameAction,
    private val searchRecipesAction: ISearchRecipesAction,
    private val fetchSearchRecipeCriteriaAction: IFetchSearchRecipeCriteriaAction
): IUserIngredientsViewModel() {

    companion object {
        const val REFRESH_SUGGESTED_INGREDIENTS_COUNT = 2
    }

    private val excludedSuggestedIngredientsIds: MutableList<Int> = mutableListOf()
    private var showedIngredients: Int = 0

    init {
        listenSource(fetchSuggestedIngredientsAction.getLiveData(), ::onFetchSuggestedIngredientsResult)
        listenSource(fetchVolumeUnitsAction.getLiveData(), ::onFetchVolumeUnitResult)
        listenSource(addVolumeUnitsAction.getLiveData(), ::onCompleteAddVolumeUnits)
        listenSource(addUserIngredientAction.getLiveData(), ::onAddIngredient)
        listenSource(fetchUserIngredientsAction.getLiveData(), ::onFetchUserIngredientsSuccess)
        listenSource(getIngredientsByNameAction.getLiveData(), ::onGetIngredientsByNameSuccess)
        listenSource(searchRecipesAction.getLiveData(), ::onSearchRecipes)
        listenSource(fetchSearchRecipeCriteriaAction.getLiveData(), ::onFetchSearchRecipeCriteriaResult)
    }

    private fun onFetchSearchRecipeCriteriaResult(result: IFetchSearchRecipeCriteriaAction.Result) {
        when(result) {
            is IFetchSearchRecipeCriteriaAction.Result.Success -> setState(State.FetchSearchRecipeCriteriaSuccess(result.recipeSearchCriteria))
        }
    }

    override fun fetchSearchRecipeCriteria() {
        fetchSearchRecipeCriteriaAction.fetch()
    }

    override fun loadVolumeUnits() {
        setState(State.Loading)
        fetchVolumeUnitsAction.fetch()
    }

    override fun fetchSuggestedIngredients(excludeIngredientId: Int) {
        if(excludeIngredientId != 0) {
            showedIngredients =+ 1
            if(showedIngredients < REFRESH_SUGGESTED_INGREDIENTS_COUNT) {
                excludedSuggestedIngredientsIds.add(excludeIngredientId)
            }
        }
        fetchSuggestedIngredientsAction.fetch(excludedSuggestedIngredientsIds)
    }

    override fun searchRecipes(searchCriteria: RecipeSearchCriteria) {
        searchRecipesAction.searchRecipes(searchCriteria)
    }

    private fun onSearchRecipes(result: ISearchRecipesAction.Result) {
        when(result) {
            is ISearchRecipesAction.Result.Success -> setState(State.SaveRecipesSuccess)
        }
    }

    override fun addIngredient(ingredient: Ingredient) {
        excludedSuggestedIngredientsIds.add(ingredient.id)
        addUserIngredientAction.add(ingredient)
    }

    private fun onFetchSuggestedIngredientsResult(result: IFetchSuggestedIngredientsAction.Result) {
        when (result) {
            is IFetchSuggestedIngredientsAction.Result.Error -> setState(State.Error(result.message))
            is IFetchSuggestedIngredientsAction.Result.Success -> setState(State.FetchSuggestedIngredientsSuccess(result.data.ingredients))
        }
    }

    private fun onFetchVolumeUnitResult(result: IFetchVolumeUnitsAction.Result) {
        when(result) {
            is IFetchVolumeUnitsAction.Result.Success -> updateVolumeUnits(result.volumeUnitsList)
            is IFetchVolumeUnitsAction.Result.Error -> setState(State.Error(result.message))
        }
    }

    private fun onCompleteAddVolumeUnits(result: IAddVolumeUnitsAction.Result) {
        when(result) {
            is IAddVolumeUnitsAction.Result.Success -> setState(State.AddVolumeUnitsSuccess)
            is IAddVolumeUnitsAction.Result.Error -> setState(State.Error(result.message))
        }
    }

    private fun updateVolumeUnits(volumeUnits: List<VolumeUnitEntity>) {
        addVolumeUnitsAction.add(volumeUnits)
    }

    private fun onAddIngredient(result: IAddUserIngredientAction.Result) {
        when(result) {
            is IAddUserIngredientAction.Result.Success -> fetchUserIngredients()
            is IAddUserIngredientAction.Result.Error -> Log.i("ERROR_ADD", result.errorMessage)
        }
    }

    override fun fetchUserIngredients() {
        fetchUserIngredientsAction.fetch()
    }

    private fun onFetchUserIngredientsSuccess(result: IFetchUserIngredientsAction.Result) {
        when(result) {
            is IFetchUserIngredientsAction.Result.Success -> updateIngredients(result)
        }
    }

    private fun updateIngredients(result: IFetchUserIngredientsAction.Result.Success) {
        setState(State.UserIngredientsUpdated(result.ingredients))
    }

    override fun searchIngredientByName(ingredientName: String) {
        getIngredientsByNameAction.getIngredients(ingredientName)
    }

    private fun onGetIngredientsByNameSuccess(result: IGetIngredientsByNameAction.Result) {

        when(result) {
            is IGetIngredientsByNameAction.Result.Success -> setState(State.SearchIngredientsByNameSuccess(result.ingredients))
        }
    }

}