package com.example.miaminstantapp.viewmodel.userfilters

import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.domain.actions.AddExcludedIngredientAction
import com.example.miaminstantapp.domain.actions.FetchExcludedIngredientsAction
import com.example.miaminstantapp.domain.actions.FetchShortIngredientsByNameAction
import com.example.miaminstantapp.domain.actions.RemoveExcludedIngredientAction
import com.example.miaminstantapp.domain.entities.ExcludedIngredientEntity
import com.example.miaminstantapp.viewmodel.BaseViewModel
import javax.inject.Inject

class AddAlergiesViewModel @Inject constructor(
    private val fetchShortIngredientsByNameAction: FetchShortIngredientsByNameAction,
    private val addExcludedIngredientAction: AddExcludedIngredientAction,
    private val fetchExcludedIngredientsAction: FetchExcludedIngredientsAction,
    private val removeExcludedIngredientAction: RemoveExcludedIngredientAction
): BaseViewModel<AddAlergiesViewModel.State>() {
    sealed class State {
        object Loading: State()
        data class IngredientsFetched(val ingredients: List<IngredientShortDto>) : State()
        data class ExcludeIngredientAdded(val ingredient: IngredientShortDto) : State()
        data class ExcludedIngredientsFetched(val ingredients: List<ExcludedIngredientEntity>): State()
        object ExcludedIngredientRemove: State()
    }

    init {
        listenSource(fetchShortIngredientsByNameAction.getLiveData(), ::fetchedSuccess)
        listenSource(addExcludedIngredientAction.getLiveData(), ::onAddedResult)
        listenSource(fetchExcludedIngredientsAction.getLiveData(), ::onFetchedResult)
        listenSource(removeExcludedIngredientAction.getLiveData(), ::onRemovedExcludeIngredient)
    }

    private fun onRemovedExcludeIngredient(result: RemoveExcludedIngredientAction.Result) {
        setState(State.ExcludedIngredientRemove)
    }

    fun fetchAdded() {
        fetchExcludedIngredientsAction.fetch()
    }

    fun add(ingredient: IngredientShortDto) {
        addExcludedIngredientAction.add(ingredient)
    }

    fun remove(ingredientId: Int) {
        removeExcludedIngredientAction.remove(ingredientId)
    }

    private fun onFetchedResult(result: FetchExcludedIngredientsAction.Result) {
        when(result) {
            is FetchExcludedIngredientsAction.Result.Success -> setState(State.ExcludedIngredientsFetched(result.ingredients))
        }
    }

    private fun onAddedResult(result: AddExcludedIngredientAction.Result) {
        when(result) {
            is AddExcludedIngredientAction.Result.Success -> {
                setState(State.ExcludeIngredientAdded(result.excludedIngredient))
            }
        }
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