package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.dtos.Ingredient

interface IGetIngredientsByNameAction: Action<IGetIngredientsByNameAction.Result> {
    sealed class Result {
        data class Success(val ingredients: List<Ingredient>): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun getIngredients(ingredientName: String)
}