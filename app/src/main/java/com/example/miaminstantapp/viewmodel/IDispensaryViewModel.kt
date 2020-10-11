package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.dtos.RecipeSearchCriteria
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits

abstract class IDispensaryViewModel: BaseViewModel<IDispensaryViewModel.State>() {

    sealed class State {
        data class Error(val error: String): State()
        object Loading: State()
        data class FetchSuggestedIngredientsSuccess(val ingredients: List<Ingredient>): State()
        object AddVolumeUnitsSuccess: State()
        data class UserIngredientsUpdated(val ingredients: List<UserIngredientWithVolumeUnits>): State()
        data class SearchIngredientsByNameSuccess(val ingredients: List<Ingredient>): State()
        object SaveRecipesSuccess: State()
        data class FetchSearchRecipeCriteriaSuccess(val criteria: RecipeSearchCriteria): State()
    }

    abstract fun loadVolumeUnits()
    abstract fun addIngredient(ingredient: Ingredient)
    abstract fun searchIngredientByName(ingredientName: String)
    abstract fun searchRecipes(searchCriteria: RecipeSearchCriteria)
    abstract fun fetchSearchRecipeCriteria()
    abstract fun fetchUserIngredients()
    abstract fun fetchSuggestedIngredients(excludeIngredientId: Int = 0)
    abstract fun removeUserIngredient(userIngredientEntity: UserIngredientEntity)
}