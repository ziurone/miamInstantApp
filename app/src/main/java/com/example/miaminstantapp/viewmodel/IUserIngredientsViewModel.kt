package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.entities.UserIngredientEntity

abstract class IUserIngredientsViewModel: BaseViewModel<IUserIngredientsViewModel.State>() {

    sealed class State {
        data class Error(val error: String): State()
        object Loading: State()
        data class FetchSuggestedIngredientsSuccess(val ingredients: List<Ingredient>): State()
        object AddVolumeUnitsSuccess: State()
        data class UserIngredientsUpdated(val ingredients: List<UserIngredientEntity>): State()
        data class SearchIngredientsByNameSuccess(val ingredients: List<Ingredient>): State()
        object AddMoneySuccess: State()
    }

    abstract fun loadMasterData()
    abstract fun addIngredient(ingredient: Ingredient)
    abstract fun searchIngredientByName(ingredientName: String)
    abstract fun setUserMoney(money: Int)
}