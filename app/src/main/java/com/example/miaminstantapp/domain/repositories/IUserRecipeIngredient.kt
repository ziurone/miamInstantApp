package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import io.reactivex.Completable

interface IUserRecipeIngredientRepository {
    fun insertAll(ingredients: List<CatalogRecipeUserIngredientEntity>): Completable
    fun add(ingredient: CatalogRecipeUserIngredientEntity): Completable
}