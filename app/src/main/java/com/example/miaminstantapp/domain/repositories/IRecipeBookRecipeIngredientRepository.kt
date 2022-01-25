package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.RecipeBookRecipeIngredientEntity
import io.reactivex.Completable
import io.reactivex.Single

interface IRecipeBookRecipeIngredientRepository {
    fun addRecipeIngredients(ingredients: List<RecipeBookRecipeIngredientEntity>): Completable
    fun fetchIngredients(recipeId: Int): Single<List<RecipeBookRecipeIngredientEntity>>
}