package com.example.miaminstantapp.viewmodel

import android.util.Log
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import com.example.miaminstantapp.domain.actions.*
import javax.inject.Inject

class UserIngredientsViewModel @Inject constructor(
    private val fetchSuggestedIngredientsUseCase: IFetchSuggestedIngredientsAction,
    private val fetchVolumeUnitsAction: IFetchVolumeUnitsAction,
    private val addVolumeUnitsAction: IAddVolumeUnitsAction,
    private val addUserIngredientAction: IAddUserIngredientAction,
    private val fetchUserIngredientsAction: IFetchUserIngredientsAction,
    private val getIngredientsByNameAction: IGetIngredientsByNameAction
): IUserIngredientsViewModel() {

    init {
        listenSource(fetchSuggestedIngredientsUseCase.getLiveData(), ::onFetchIngredientsResult)
        listenSource(fetchVolumeUnitsAction.getLiveData(), ::onFetchVolumeUnitResult)
        listenSource(addVolumeUnitsAction.getLiveData(), ::onCompleteAddVolumeUnits)
        listenSource(addUserIngredientAction.getLiveData(), ::onAddIngredient)
        listenSource(fetchUserIngredientsAction.getLiveData(), ::onFetchUserIngredientsSuccess)
        listenSource(getIngredientsByNameAction.getLiveData(), ::onGetIngredientsByNameSuccess)
    }

    override fun loadMasterData() {
        setState(State.Loading)
        fetchSuggestedIngredientsUseCase.fetch()
        fetchVolumeUnitsAction.fetch()
    }

    override fun addIngredient(ingredient: Ingredient) {
        addUserIngredientAction.add(ingredient)
    }

    private fun onFetchIngredientsResult(result: IFetchSuggestedIngredientsAction.Result) {
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

    private fun fetchUserIngredients() {
        fetchUserIngredientsAction.fetch()
        Log.i("INGREDIENT", "Ingredient added")
    }

    private fun onFetchUserIngredientsSuccess(result: IFetchUserIngredientsAction.Result) {
        when(result) {
            is IFetchUserIngredientsAction.Result.Success -> updateIngredients(result)
        }
    }

    private fun updateIngredients(result: IFetchUserIngredientsAction.Result.Success) {
        Log.i("Ingrediente agregado", result.ingredients.last().name)
        setState(State.UserIngredientsUpdated(result.ingredients))
    }

    override fun searchIngredientByName(ingredientName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onGetIngredientsByNameSuccess(result: IGetIngredientsByNameAction.Result) {

        when(result) {
            is IGetIngredientsByNameAction.Result.Success -> setState(State.SearchIngredientsByNameSuccess(result.ingredients))
        }
    }

}