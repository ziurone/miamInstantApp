package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.usecases.IFetchSuggestedIngredientsAction
import com.example.miaminstantapp.domain.usecases.IFetchVolumeUnitsAction
import javax.inject.Inject

class UserIngredientsViewModel @Inject constructor(
    private val fetchSuggestedIngredientsUseCase: IFetchSuggestedIngredientsAction,
    private val fetchVolumeUnitsAction: IFetchVolumeUnitsAction
): IUserIngredientsViewModel() {

    init {
        listenSource(fetchSuggestedIngredientsUseCase.getLiveData(), ::onFetchIngredientsResult)
        listenSource(fetchVolumeUnitsAction.getLiveData(), ::onFetchVolumeUnitResult)
    }

    override fun loadMasterData() {
        setState(State.Loading)
        fetchSuggestedIngredientsUseCase.fetch()
        fetchVolumeUnitsAction.fetch()
    }

    private fun onFetchIngredientsResult(result: IFetchSuggestedIngredientsAction.Result) {
        when (result) {
            is IFetchSuggestedIngredientsAction.Result.Error -> setState(State.Error(result.message))
            is IFetchSuggestedIngredientsAction.Result.Success -> setState(State.FetchSuggestedIngredientsSuccess(result.data.ingredients))
        }
    }

    private fun onFetchVolumeUnitResult(result: IFetchVolumeUnitsAction.Result) {
        when(result) {
            is IFetchVolumeUnitsAction.Result.Success -> setState(State.FetchVolumeUnitsSuccess(result.volumeUnitsList))
            is IFetchVolumeUnitsAction.Result.Error -> setState(State.Error(result.message))
        }
    }

}