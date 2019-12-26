package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.dtos.IngredientsListResponse
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import io.reactivex.Completable
import io.reactivex.Single

interface IIngredientRepository {
    fun getSuggestedIngredients(): Single<IngredientsListResponse>
    fun addIngredient(ingredient: Ingredient): Completable
    fun getUserIngredients(): Single<List<UserIngredientEntity>>
    fun getIngredientsByName(ingredientName: String): Single<IngredientsListResponse>
}