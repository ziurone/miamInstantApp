package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.miaminstantapp.domain.entities.MarketRecipeEntity
import com.example.miaminstantapp.domain.relations.DoableRecipe
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MarketRecipeDao {
    @Insert
    fun insertAll(recipes: List<MarketRecipeEntity>): Completable

    @Query("Select * FROM " + MarketRecipeEntity.TABLE_NAME)
    fun fetchAll(): Single<List<DoableRecipe>>

    @Query("SELECT * FROM " + MarketRecipeEntity.TABLE_NAME + " WHERE id = :id ")
    fun fetchById(id: Int): Single<DoableRecipe>

}