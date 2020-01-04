package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.miaminstantapp.domain.entities.MarketRecipeEntity
import com.example.miaminstantapp.domain.entities.RecipeWithUserIngredients
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MarketRecipeDao {
    @Insert
    fun insertAll(recipes: List<MarketRecipeEntity>): Completable

    @Query("Select * FROM " + MarketRecipeEntity.TABLE_NAME)
    fun fetchAll(): Single<List<RecipeWithUserIngredients>>
}