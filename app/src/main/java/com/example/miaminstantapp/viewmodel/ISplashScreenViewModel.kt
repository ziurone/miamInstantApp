package com.example.miaminstantapp.viewmodel

abstract class ISplashScreenViewModel: BaseViewModel<ISplashScreenViewModel.State>() {
    sealed class State {
        object IsUserFirstTimeInApp: State()
        object UserHasAlreadyUseTheApp: State()
    }

    abstract fun hasUserUsedAppBefore()
}