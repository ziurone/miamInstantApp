package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import io.reactivex.Completable
import io.reactivex.Single

interface IRecipeBookRepository {
    fun addRecipe(recipe: RecipeBookRecipeEntity): Completable
    fun fetchRecipesDesc(made: Boolean): Single<List<RecipeBookRecipeEntity>>
    fun fetchRecipeById(recipeId: Int): Single<RecipeBookRecipeEntity>
    fun setRecipeAsMade(recipeId: Int): Completable
    fun fetchRecipeBookRecipesIds(): Single<List<Int>>
}