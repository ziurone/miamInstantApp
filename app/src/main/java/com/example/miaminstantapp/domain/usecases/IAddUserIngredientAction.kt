package com.example.miaminstantapp.domain.usecases

import com.example.miaminstantapp.domain.dtos.Ingredient
import io.reactivex.Completable

interface IAddUserIngredientAction: Action<IAddUserIngredientAction.Result> {
    sealed class Result {
        object Success: Result()
        data class Error(val errorMessage: String): Result()
    }

    abstract fun add(ingredient: Ingredient)
}