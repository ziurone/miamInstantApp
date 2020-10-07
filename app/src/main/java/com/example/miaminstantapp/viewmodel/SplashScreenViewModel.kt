package com.example.miaminstantapp.viewmodel

import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.viewModelScope
import com.example.miaminstantapp.domain.actions.IIsFirstTimeOpeningAppAction
import com.example.miaminstantapp.utils.Delayer
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val isFirstTimeOpeningAppAction: IIsFirstTimeOpeningAppAction,
    private val delayer: Delayer
): ISplashScreenViewModel() {

    init {
        listenSource(isFirstTimeOpeningAppAction.getLiveData(), ::onIsUserFirstTimeInApp)
    }

    override fun hasUserUsedAppBefore() {
        isFirstTimeOpeningAppAction.isFirstTimeInApp()
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
}