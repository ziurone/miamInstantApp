package com.example.miaminstantapp.viewmodel.userfilters

import com.example.miaminstantapp.domain.enums.Diet
import com.example.miaminstantapp.viewmodel.BaseViewModel

abstract class IAddUserDietsViewModel: BaseViewModel<IAddUserDietsViewModel.State>() {
    sealed class State {
        data class DietsFetched(val diets: List<Diet>): State()
        object Loading: State()
    }

    abstract fun addUserDiet(diet: Diet)
    abstract fun fetchDiets()
}