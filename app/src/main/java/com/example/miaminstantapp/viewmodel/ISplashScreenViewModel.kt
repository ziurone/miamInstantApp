package com.example.miaminstantapp.viewmodel

abstract class ISplashScreenViewModel: BaseViewModel<ISplashScreenViewModel.State>() {
    sealed class State {
        object IsUserFirstTimeInApp: State()
        object UserHasAlreadyUseTheApp: State()
        object MasterDataRetrievedSuccessfully: State()
    }

    abstract fun hasUserUsedAppBefore()
    abstract fun fetchMasterData()
}