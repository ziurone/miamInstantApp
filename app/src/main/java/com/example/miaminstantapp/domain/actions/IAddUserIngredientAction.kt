package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.dtos.Ingredient

interface IAddUserIngredientAction: Action<IAddUserIngredientAction.Result> {
    sealed class Result {
        object Success: Result()
        data class Error(val errorMessage: String): Result()
    }

    fun add(ingredient: Ingredient)
}