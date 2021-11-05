package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelations

abstract class ICatalogRecipesListViewModel: BaseViewModel<ICatalogRecipesListViewModel.State>() {
    sealed class State {
        data class FetchedRecipesSuccess(val catalogRecipeRelations: List<CatalogRecipeRelations>): State()
        data class Error(val errorMessage: String): State()
        data class FetchSuggestedIngredientsSuccess(val suggestedIngredients: List<Ingredient>) : State()
    }

    abstract fun fetchRecipes()
    abstract fun fetchSuggestedIngredients()
    abstract fun addIngredient(ingredient: Ingredient)
}