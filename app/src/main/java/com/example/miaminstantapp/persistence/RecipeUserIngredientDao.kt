package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import io.reactivex.Completable

@Dao
interface RecipeUserIngredientDao {
    @Insert
    fun insertAll(ingredients: List<CatalogRecipeUserIngredientEntity>): Completable
}