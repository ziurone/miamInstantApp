package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.FetchUserIngredientWithVolumeUnitsAction
import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits
import javax.inject.Inject

class EditUserIngredientViewModel @Inject constructor(
    private val fetchUserIngredientWithVolumeUnitsAction: FetchUserIngredientWithVolumeUnitsAction
) : BaseViewModel<EditUserIngredientViewModel.State>() {

    sealed class State {
        data class FetchIngredientSuccess(val ingredient: UserIngredientWithVolumeUnits): State()
        object UserEditSuccess: State()
        object Loading: State()
    }

    init {
        listenSource(fetchUserIngredientWithVolumeUnitsAction.getLiveData(), ::onFetchIngredientResult)
    }

    private fun onFetchIngredientResult(result: FetchUserIngredientWithVolumeUnitsAction.Result) {
        when(result) {
            is FetchUserIngredientWithVolumeUnitsAction.Result.Success -> {
                setState(State.FetchIngredientSuccess(result.ingredient))
            }
            FetchUserIngredientWithVolumeUnitsAction.Result.Error -> {}
        }
    }

    fun fetchIngredient(ingredientId: Int) {
        fetchUserIngredientWithVolumeUnitsAction.fetch(ingredientId)
    }
}
