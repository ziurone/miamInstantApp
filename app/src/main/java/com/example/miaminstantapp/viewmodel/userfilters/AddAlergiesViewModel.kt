package com.example.miaminstantapp.viewmodel.userfilters

import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.domain.actions.FetchShortIngredientsByNameAction
import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class AddAlergiesViewModel @Inject constructor(
    private val fetchShortIngredientsByNameAction: FetchShortIngredientsByNameAction
): BaseViewModel<AddAlergiesViewModel.State>() {
    sealed class State {
        object Loading: State()
        data class IngredientsFetched(val ingredients: List<IngredientShortDto>) : State()
    }

    init {
        listenSource(fetchShortIngredientsByNameAction.getLiveData(), ::fetchedSuccess)
    }

    fun search(queryText: String) {
        fetchShortIngredientsByNameAction.search(queryText)
    }

    private fun fetchedSuccess(result: FetchShortIngredientsByNameAction.Result) {
        when(result) {
            is FetchShortIngredientsByNameAction.Result.Success -> setState(State.IngredientsFetched(result.ingredients))
        }
    }
}