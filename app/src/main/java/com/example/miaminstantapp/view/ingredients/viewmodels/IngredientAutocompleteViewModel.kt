package com.example.miaminstantapp.view.ingredients.viewmodels

import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class IngredientAutocompleteViewModel @Inject constructor(): BaseViewModel<IngredientAutocompleteViewModel.State>() {
    sealed class State {
        data class IngredientsRetrieved(val ingredients: Ingredient): State()
        object Loading: State()
    }
}