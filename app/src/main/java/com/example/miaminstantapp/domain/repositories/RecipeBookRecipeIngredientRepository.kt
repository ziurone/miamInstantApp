package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.RecipeBookRecipeIngredientEntity
import com.example.miaminstantapp.persistence.RecipeBookRecipeIngredientDao
import io.reactivex.Completable
import javax.inject.Inject

class RecipeBookRecipeIngredientRepository @Inject constructor(
    private val recipeBookRecipeIngredientDao: RecipeBookRecipeIngredientDao
): IRecipeBookRecipeIngredientRepository {
    override fun addRecipeIngredients(ingredients: List<RecipeBookRecipeIngredientEntity>): Completable = recipeBookRecipeIngredientDao.insertAll(ingredients)
}