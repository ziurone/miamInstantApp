package com.example.miaminstantapp.view.ingredients.viewmodels

import android.util.Log
import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.domain.actions.AddUserIngredientAction
import com.example.miaminstantapp.domain.actions.IAddUserIngredientAction
import com.example.miaminstantapp.domain.actions.IGetIngredientsByNameAction
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.viewmodel.BaseViewModel
import com.example.miaminstantapp.viewmodel.IDispensaryViewModel
import javax.inject.Inject

class IngredientAutocompleteViewModel @Inject constructor(
    private val getIngredientsByNameAction: IGetIngredientsByNameAction,
    private val addUserIngredientAction: AddUserIngredientAction
): BaseViewModel<IngredientAutocompleteViewModel.State>() {

    sealed class State {
        data class IngredientsRetrieved(val ingredients: List<Ingredient>): State()
        object Loading: State()
        object AddUserIngredientSuccess: State()
    }

    init {
        listenSource(getIngredientsByNameAction.getLiveData(), ::onGetIngredientsByNameSuccess)
        listenSource(addUserIngredientAction.getLiveData(), ::onAddUserIngredient)
    }

    fun addUserIngredient(ingredient: Ingredient) {
        addUserIngredientAction.add(ingredient)
    }

    fun searchIngredientByName(ingredientName: String) {
        if(ingredientName.length < 3) return
        setState(State.Loading)
        getIngredientsByNameAction.getIngredients(ingredientName)
    }

    private fun onGetIngredientsByNameSuccess(result: IGetIngredientsByNameAction.Result) {
        when(result) {
            is IGetIngredientsByNameAction.Result.Success -> setState(State.IngredientsRetrieved(result.ingredients))
        }
    }

    private fun onAddUserIngredient(result: IAddUserIngredientAction.Result) {
        when(result) {
            is IAddUserIngredientAction.Result.Error -> Log.i("error", result.errorMessage)
            IAddUserIngredientAction.Result.Success -> setState(State.AddUserIngredientSuccess)
        }
    }
}