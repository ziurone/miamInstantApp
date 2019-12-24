package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.UserIngredientEntity

interface IFetchUserIngredientsAction: Action<IFetchUserIngredientsAction.Result> {
    sealed class Result {
        data class Success(val ingredients: List<UserIngredientEntity>): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetch()
}