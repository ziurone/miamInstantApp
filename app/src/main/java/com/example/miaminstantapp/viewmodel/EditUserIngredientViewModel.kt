package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits
import javax.inject.Inject

class EditUserIngredientViewModel @Inject constructor() : BaseViewModel<EditUserIngredientViewModel.State>() {

    sealed class State {
        data class FetchIngredientSuccess(val ingredient: UserIngredientWithVolumeUnits): State()
        object UserEditSuccess: State()
        object Loading: State()
    }

    fun fetchIngredient(ingredientId: Int) {

    }
}
