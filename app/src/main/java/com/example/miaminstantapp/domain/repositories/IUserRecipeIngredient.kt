package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.DoableRecipeUserIngredient
import io.reactivex.Completable

interface IUserRecipeIngredientRepository {
    fun insertAll(ingredients: List<DoableRecipeUserIngredient>): Completable
}