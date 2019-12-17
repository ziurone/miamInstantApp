package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import com.example.miaminstantapp.domain.usecases.IAddVolumeUnitsAction
import com.example.miaminstantapp.domain.usecases.IFetchSuggestedIngredientsAction
import com.example.miaminstantapp.domain.usecases.IFetchVolumeUnitsAction
import javax.inject.Inject

class UserIngredientsViewModel @Inject constructor(
    private val fetchSuggestedIngredientsUseCase: IFetchSuggestedIngredientsAction,
    private val fetchVolumeUnitsAction: IFetchVolumeUnitsAction,
    private val addVolumeUnitsAction: IAddVolumeUnitsAction
): IUserIngredientsViewModel() {

    init {
        listenSource(fetchSuggestedIngredientsUseCase.getLiveData(), ::onFetchIngredientsResult)
        listenSource(fetchVolumeUnitsAction.getLiveData(), ::onFetchVolumeUnitResult)
        listenSource(addVolumeUnitsAction.getLiveData(), ::onCompleteAddVolumeUnits)
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
            is IFetchVolumeUnitsAction.Result.Success -> updateVolumeUnits(result.volumeUnitsList)
            is IFetchVolumeUnitsAction.Result.Error -> setState(State.Error(result.message))
        }
    }

    private fun onCompleteAddVolumeUnits(result: IAddVolumeUnitsAction.Result) {
        when(result) {
            is IAddVolumeUnitsAction.Result.Success -> setState(State.AddVolumeUnitsSuccess)
            is IAddVolumeUnitsAction.Result.Error -> setState(State.Error(result.message))
        }
    }

    private fun updateVolumeUnits(volumeUnits: List<VolumeUnitEntity>) {
        addVolumeUnitsAction.add(volumeUnits)
    }

}