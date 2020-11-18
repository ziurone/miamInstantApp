package com.example.miaminstantapp.view

import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits
import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class EditUserIngredientViewModel @Inject constructor() : BaseViewModel<EditUserIngredientViewModel.State>() {

    sealed class State {
        data class FetchIngredientSuccess(val ingredient: UserIngredientWithVolumeUnits): State()
        object UserEditSuccess: State()
        object Loading: State()
    }
}
