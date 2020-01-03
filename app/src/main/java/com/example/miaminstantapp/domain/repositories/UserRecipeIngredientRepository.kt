package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.UserIngredientDTO
import com.example.miaminstantapp.domain.entities.RecipeUserIngredientEntity
import com.example.miaminstantapp.persistence.UserRecipeIngredientDao
import io.reactivex.Completable
import javax.inject.Inject

class UserRecipeIngredientRepository @Inject constructor(
    private val userRecipeIngredientDao: UserRecipeIngredientDao
): IUserRecipeIngredientRepository {
    override fun insertAll(ingredients: List<RecipeUserIngredientEntity>): Completable = userRecipeIngredientDao.insertAll(ingredients)
}