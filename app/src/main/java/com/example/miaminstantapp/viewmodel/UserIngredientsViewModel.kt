package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.usecases.IFetchSuggestedIngredientsUseCase

class UserIngredientsViewModel (
    private val fetchSuggestedIngredientsUseCase: IFetchSuggestedIngredientsUseCase
): IUserIngredientsViewModel() {

    init {
        listenSource(fetchSuggestedIngredientsUseCase.getLiveData(), ::onFetchIngredientsResult)
    }

    override fun fetchSuggestedIngredients() {
        setState(State.Loading)
        fetchSuggestedIngredientsUseCase.fetch()
    }

    private fun onFetchIngredientsResult(result: IFetchSuggestedIngredientsUseCase.Result) {
        when (result) {
            is IFetchSuggestedIngredientsUseCase.Result.Error -> setState(State.Error(result.message))
            is IFetchSuggestedIngredientsUseCase.Result.Success -> setState(State.FetchSuggestedIngredientsSuccess(result.data.ingredients))
        }
    }

}