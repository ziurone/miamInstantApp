package com.example.miaminstantapp.viewmodel.userfilters

import com.example.miaminstantapp.domain.entities.DietEntity
import com.example.miaminstantapp.domain.enums.Diet
import com.example.miaminstantapp.viewmodel.BaseViewModel

abstract class IAddUserDietsViewModel: BaseViewModel<IAddUserDietsViewModel.State>() {
    sealed class State {
        data class DietsFetched(val diets: List<Diet>): State()
        data class UserDietsFetched(val diets: List<DietEntity>): State()
        object Loading: State()
    }

    abstract fun addUserDiet(diet: Diet)
    abstract fun removeDiet(diet: Diet)
    abstract fun fetchDiets()
}