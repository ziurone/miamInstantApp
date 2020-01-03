package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.RecipeUserIngredientEntity
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import io.reactivex.Completable

interface IUserRecipeIngredientRepository {
    fun insertAll(ingredients: List<RecipeUserIngredientEntity>): Completable
}