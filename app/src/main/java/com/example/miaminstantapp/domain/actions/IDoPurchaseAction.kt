package com.example.miaminstantapp.domain.actions

interface IDoPurchaseAction: Action<IDoPurchaseAction.Result> {
    sealed class Result {
        object Success: Result()
        data class Error(val errorMessage: String)
    }

    fun doPurchase()
}