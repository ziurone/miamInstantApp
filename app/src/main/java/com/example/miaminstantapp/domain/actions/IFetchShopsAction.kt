package com.example.miaminstantapp.domain.actions

interface IFetchShopsAction: Action<IFetchShopsAction.Result> {
    sealed class Result {
        object Success: Result()
        object Error: Result()
    }

    fun fetchShopsAndBranches(lat: String, long: String, squares: Int)
}