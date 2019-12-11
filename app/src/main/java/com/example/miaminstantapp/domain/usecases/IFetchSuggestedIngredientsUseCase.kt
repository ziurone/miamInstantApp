package com.example.miaminstantapp.domain.usecases

import com.example.miaminstantapp.domain.dtos.SuggestedIngredientsResponse

interface IFetchSuggestedIngredientsUseCase: UseCase<IFetchSuggestedIngredientsUseCase.Result> {
    sealed class Result {
        data class Success(val data: SuggestedIngredientsResponse): Result()
        data class Error(val message: String): Result()
    }

    fun fetch()
}