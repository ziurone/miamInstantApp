package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import com.example.miaminstantapp.persistence.CatalogRecipeUserIngredientDao
import io.reactivex.Completable
import javax.inject.Inject

class UserRecipeIngredientRepository @Inject constructor(
    private val userCatalogRecipeIngredientDao: CatalogRecipeUserIngredientDao
): IUserRecipeIngredientRepository {
    override fun insertAll(ingredients: List<CatalogRecipeUserIngredientEntity>): Completable = userCatalogRecipeIngredientDao.insertAll(ingredients)
    override fun add(ingredient: CatalogRecipeUserIngredientEntity): Completable = userCatalogRecipeIngredientDao.insert(ingredient)

}