package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import com.example.miaminstantapp.domain.entities.UserAddressEntity
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
        object FetchShopsSuccess: State()
        object SaveRecipesSuccess: State()
        data class FetchSearchRecipeCriteriaSuccess(val criteria: RecipeSearchCriteria): State()
        data class FetchCurrentUserAddressSucess(val address: UserAddressEntity?): State()
    }

    abstract fun loadVolumeUnits()
    abstract fun addIngredient(ingredient: Ingredient)
    abstract fun searchIngredientByName(ingredientName: String)
    abstract fun setUserMoney(money: Int)
    abstract fun fetchZoneShops(lat: String, long: String, squares: Int)
    abstract fun searchRecipes(searchCriteria: RecipeSearchCriteria)
    abstract fun fetchSearchRecipeCriteria()
    abstract fun fetchUserIngredients()
    abstract fun fetchSuggestedIngredients(ingredients: List<UserIngredientEntity>)
    abstract fun addAddress(userAddress: UserAddressEntity)
    abstract fun fetchCurrentAddress()
}