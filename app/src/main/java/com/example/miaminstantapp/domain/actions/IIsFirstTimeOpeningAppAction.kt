package com.example.miaminstantapp.domain.actions

interface IIsFirstTimeOpeningAppAction: Action<IIsFirstTimeOpeningAppAction.Result> {
    sealed class Result {
        object UserHasUsedApp: Result()
        object UserFirstTimeInApp: Result()
    }

    fun isFirstTimeInApp()
}