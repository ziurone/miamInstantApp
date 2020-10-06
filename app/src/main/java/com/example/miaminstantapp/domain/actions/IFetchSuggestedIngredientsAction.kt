package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.dtos.IngredientsListResponse
import com.example.miaminstantapp.domain.entities.UserIngredientEntity

interface IFetchSuggestedIngredientsAction: Action<IFetchSuggestedIngredientsAction.Result> {
    sealed class Result {
        data class Success(val data: IngredientsListResponse): Result()
        data class Error(val message: String): Result()
    }

    fun fetch(excludeIngredientsIds: List<Int>)
}