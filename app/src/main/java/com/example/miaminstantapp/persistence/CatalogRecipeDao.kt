package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.miaminstantapp.domain.entities.CatalogRecipeEntity
import com.example.miaminstantapp.domain.relations.CatalogRecipe
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CatalogRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(recipes: List<CatalogRecipeEntity>): Completable

    @Query("Select * FROM " + CatalogRecipeEntity.TABLE_NAME)
    fun fetchAll(): Single<List<CatalogRecipe>>

    @Query("SELECT * FROM " + CatalogRecipeEntity.TABLE_NAME + " WHERE id = :id ")
    fun fetchById(id: Int): Single<CatalogRecipe>

    @Query("DELETE FROM " + CatalogRecipeEntity.TABLE_NAME)
    fun deleteAll(): Completable
}