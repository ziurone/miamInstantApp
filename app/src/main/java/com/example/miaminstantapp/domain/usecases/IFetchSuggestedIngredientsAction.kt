package com.example.miaminstantapp.domain.usecases

import com.example.miaminstantapp.domain.dtos.SuggestedIngredientsResponse

interface IFetchSuggestedIngredientsAction: Action<IFetchSuggestedIngredientsAction.Result> {
    sealed class Result {
        data class Success(val data: SuggestedIngredientsResponse): Result()
        data class Error(val message: String): Result()
    }

    fun fetch()
}