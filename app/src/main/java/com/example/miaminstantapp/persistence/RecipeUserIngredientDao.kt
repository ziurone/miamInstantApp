package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import io.reactivex.Completable

@Dao
interface RecipeUserIngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(ingredients: List<CatalogRecipeUserIngredientEntity>): Completable
}