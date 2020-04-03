package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.DoableRecipeUserIngredientEntity
import com.example.miaminstantapp.persistence.RecipeUserIngredientDao
import io.reactivex.Completable
import javax.inject.Inject

class UserRecipeIngredientRepository @Inject constructor(
    private val userRecipeIngredientDao: RecipeUserIngredientDao
): IUserRecipeIngredientRepository {
    override fun insertAll(ingredients: List<DoableRecipeUserIngredientEntity>): Completable = userRecipeIngredientDao.insertAll(ingredients)
}