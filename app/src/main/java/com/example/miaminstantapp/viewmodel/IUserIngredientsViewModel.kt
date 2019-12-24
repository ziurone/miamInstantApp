package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.dtos.Ingredient

abstract class IUserIngredientsViewModel: BaseViewModel<IUserIngredientsViewModel.State>() {

    sealed class State {
        data class Error(val error: String): State()
        object Loading: State()
        data class FetchSuggestedIngredientsSuccess(val ingredients: List<Ingredient>): State()
        object AddVolumeUnitsSuccess: State()
    }

    abstract fun loadMasterData()
    abstract fun addIngredient(ingredient: Ingredient)
}