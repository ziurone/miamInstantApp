package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.RecipeBookRecipeIngredientEntity
import io.reactivex.Completable

interface IRecipeBookRecipeIngredientRepository {
    fun addRecipeIngredients(ingredients: List<RecipeBookRecipeIngredientEntity>): Completable
}