package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.domain.actions.IIsFirstTimeOpeningAppAction
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val isFirstTimeOpeningAppAction: IIsFirstTimeOpeningAppAction
): ISplashScreenViewModel() {

    init {
        listenSource(isFirstTimeOpeningAppAction.getLiveData(), ::onIsUserFirstTimeInApp)
    }

    override fun hasUserUsedAppBefore() {
        isFirstTimeOpeningAppAction.isFirstTimeInApp()
    }

    private fun onIsUserFirstTimeInApp(result: IIsFirstTimeOpeningAppAction.Result) {
        when(result) {
            is IIsFirstTimeOpeningAppAction.Result.UserFirstTimeInApp -> {
                setState(ISplashScreenViewModel.State.IsUserFirstTimeInApp)

            }
            is IIsFirstTimeOpeningAppAction.Result.UserHasUsedApp -> setState(ISplashScreenViewModel.State.UserHasAlreadyUseTheApp)
        }
    }
}