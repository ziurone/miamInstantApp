package com.example.miaminstantapp.persistence

import androidx.room.*
import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import io.reactivex.Completable

@Dao
interface CatalogRecipeUserIngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(ingredients: List<CatalogRecipeUserIngredientEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ingredient: CatalogRecipeUserIngredientEntity): Completable

    @Query("DELETE FROM " + CatalogRecipeUserIngredientEntity.TABLE_NAME)
    fun clear(): Completable
}