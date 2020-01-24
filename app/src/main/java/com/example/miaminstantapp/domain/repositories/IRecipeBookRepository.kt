package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import io.reactivex.Completable

interface IRecipeBookRepository {
    fun addRecipe(recipe: RecipeBookRecipeEntity): Completable
}