package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.dtos.SuggestedIngredientsResponse
import io.reactivex.Completable
import io.reactivex.Single

interface IIngredientRepository {
    fun getSuggestedIngredients(): Single<SuggestedIngredientsResponse>
    fun addIngredient(ingredient: Ingredient): Completable
}