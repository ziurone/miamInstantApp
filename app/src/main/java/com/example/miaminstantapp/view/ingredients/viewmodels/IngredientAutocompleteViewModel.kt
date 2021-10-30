package com.example.miaminstantapp.view.ingredients.viewmodels

import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.domain.actions.IGetIngredientsByNameAction
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.viewmodel.BaseViewModel
import com.example.miaminstantapp.viewmodel.IDispensaryViewModel
import javax.inject.Inject

class IngredientAutocompleteViewModel @Inject constructor(
    private val getIngredientsByNameAction: IGetIngredientsByNameAction
): BaseViewModel<IngredientAutocompleteViewModel.State>() {

    sealed class State {
        data class IngredientsRetrieved(val ingredients: List<Ingredient>): State()
        object Loading: State()
    }

    init {
        listenSource(getIngredientsByNameAction.getLiveData(), ::onGetIngredientsByNameSuccess)
    }

    fun searchIngredientByName(ingredientName: String) {
        getIngredientsByNameAction.getIngredients(ingredientName)
    }

    private fun onGetIngredientsByNameSuccess(result: IGetIngredientsByNameAction.Result) {

        when(result) {
            is IGetIngredientsByNameAction.Result.Success -> setState(IngredientAutocompleteViewModel.State.IngredientsRetrieved(result.ingredients))
        }
    }
}