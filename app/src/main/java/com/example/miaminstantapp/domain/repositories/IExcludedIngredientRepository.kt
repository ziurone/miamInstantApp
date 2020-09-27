package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.domain.entities.ExcludedIngredientEntity
import io.reactivex.Completable
import io.reactivex.Single

interface IExcludedIngredientRepository {
    fun add(ingredient: IngredientShortDto): Completable
    fun fetchAll(): Single<List<ExcludedIngredientEntity>>
    fun remove(ingredientId: Int): Completable
}