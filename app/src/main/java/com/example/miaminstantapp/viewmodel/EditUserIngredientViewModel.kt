package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.EditUserIngredientAction
import com.example.miaminstantapp.domain.actions.FetchUserIngredientWithVolumeUnitsAction
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits
import javax.inject.Inject

class EditUserIngredientViewModel @Inject constructor(
    private val fetchUserIngredientWithVolumeUnitsAction: FetchUserIngredientWithVolumeUnitsAction,
    private val editUserIngredientAction: EditUserIngredientAction
) : BaseViewModel<EditUserIngredientViewModel.State>() {

    sealed class State {
        data class FetchIngredientSuccess(val ingredient: UserIngredientWithVolumeUnits): State()
        object IngredientEditSuccess: State()
        object Loading: State()
    }

    init {
        listenSource(fetchUserIngredientWithVolumeUnitsAction.getLiveData(), ::onFetchIngredientResult)
        listenSource(editUserIngredientAction.getLiveData(), ::onEditVolumeUnitSuccess)
    }

    private fun onFetchIngredientResult(result: FetchUserIngredientWithVolumeUnitsAction.Result) {
        when(result) {
            is FetchUserIngredientWithVolumeUnitsAction.Result.Success -> {
                setState(State.FetchIngredientSuccess(result.ingredient))
            }
            FetchUserIngredientWithVolumeUnitsAction.Result.Error -> {}
        }
    }

    private fun onEditVolumeUnitSuccess(result: EditUserIngredientAction.Result) {
        when(result) {
            EditUserIngredientAction.Result.EditedSuccess -> setState(State.IngredientEditSuccess)
        }
    }

    fun fetchIngredient(ingredientId: Int) {
        fetchUserIngredientWithVolumeUnitsAction.fetch(ingredientId)
    }

    fun editVolumeUnit(ingredientId: Int, volumeUnitId: Int, volumUnitQuantity: Int) {
        editUserIngredientAction.edit(ingredientId, volumeUnitId, volumUnitQuantity)
    }
}
