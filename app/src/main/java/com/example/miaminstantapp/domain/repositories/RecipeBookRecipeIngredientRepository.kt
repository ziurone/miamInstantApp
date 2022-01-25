package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.RecipeBookRecipeIngredientEntity
import com.example.miaminstantapp.persistence.RecipeBookRecipeIngredientDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RecipeBookRecipeIngredientRepository @Inject constructor(
    private val recipeBookRecipeIngredientDao: RecipeBookRecipeIngredientDao
): IRecipeBookRecipeIngredientRepository {
    override fun addRecipeIngredients(ingredients: List<RecipeBookRecipeIngredientEntity>): Completable = recipeBookRecipeIngredientDao.insertAll(ingredients)

    override fun fetchIngredients(recipeId: Int): Single<List<RecipeBookRecipeIngredientEntity>> = recipeBookRecipeIngredientDao.fetch(recipeId)
}