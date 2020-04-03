package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.DoableRecipeUserIngredientEntity
import io.reactivex.Completable

interface IUserRecipeIngredientRepository {
    fun insertAll(ingredients: List<DoableRecipeUserIngredientEntity>): Completable
}