package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.miaminstantapp.domain.entities.CatalogRecipeEntity
import com.example.miaminstantapp.domain.entities.CatalogRecipeEntityLegacy
import com.example.miaminstantapp.domain.relations.CatalogRecipeAgreggate
import com.example.miaminstantapp.domain.relations.CatalogRecipeRelationsLegacy
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CatalogRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLegacy(recipeLegacies: List<CatalogRecipeEntityLegacy>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(recipeLegacies: List<CatalogRecipeEntity>): Completable

    @Query("Select * FROM " + CatalogRecipeEntity.TABLE_NAME)
    fun fetchAll(): Single<List<CatalogRecipeAgreggate>>

    @Query("SELECT * FROM " + CatalogRecipeEntity.TABLE_NAME + " WHERE id = :id ")
    fun fetchById(id: Int): Single<CatalogRecipeAgreggate>

    @Query("DELETE FROM " + CatalogRecipeEntityLegacy.TABLE_NAME)
    fun deleteAll(): Completable
}