package com.example.miaminstantapp.viewmodel

import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.viewModelScope
import com.example.miaminstantapp.domain.actions.*
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import com.example.miaminstantapp.utils.Delayer
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val isFirstTimeOpeningAppAction: IIsFirstTimeOpeningAppAction,
    private val delayer: Delayer,
    private val fetchVolumeUnitsAction: FetchVolumeUnitsAction,
    private val addVolumeUnitsAction: AddVolumeUnitsAction
): ISplashScreenViewModel() {

    init {
        listenSource(isFirstTimeOpeningAppAction.getLiveData(), ::onIsUserFirstTimeInApp)
        listenSource(fetchVolumeUnitsAction.getLiveData(), ::onFetchVolumeUnitsAction)
        listenSource(addVolumeUnitsAction.getLiveData(), ::onCompleteAddVolumeUnits)
    }

    override fun hasUserUsedAppBefore() {
        isFirstTimeOpeningAppAction.isFirstTimeInApp()
    }



    override fun fetchMasterData() {
        fetchVolumeUnitsAction.fetch()
    }

    private fun onIsUserFirstTimeInApp(result: IIsFirstTimeOpeningAppAction.Result) {
        viewModelScope.launch {
            delayer.delay(900)
            when(result) {
                is IIsFirstTimeOpeningAppAction.Result.UserFirstTimeInApp -> {
                    setState(State.IsUserFirstTimeInApp)

                }
                is IIsFirstTimeOpeningAppAction.Result.UserHasUsedApp -> setState(State.UserHasAlreadyUseTheApp)
            }
        }
    }

    private fun onFetchVolumeUnitsAction(result: IFetchVolumeUnitsAction.Result) {
        when(result) {
            is IFetchVolumeUnitsAction.Result.Error -> Unit
            is IFetchVolumeUnitsAction.Result.Success -> updateVolumeUnits(result.volumeUnitsList)
        }
    }

    private fun updateVolumeUnits(volumeUnits: List<VolumeUnitEntity>) {
        addVolumeUnitsAction.add(volumeUnits)
        setState(ISplashScreenViewModel.State.MasterDataRetrievedSuccessfully)
    }

    private fun onCompleteAddVolumeUnits(result: IAddVolumeUnitsAction.Result) {

    }
}