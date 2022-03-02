package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.miaminstantapp.domain.entities.CatalogRecipeMarketIngredientEntity
import com.example.miaminstantapp.domain.entities.MarketIngredientEntityLegacy
import io.reactivex.Completable

@Dao
interface MarketIngredientDao {
    @Insert
    fun insertAllLegacy(marketsIngredientLegacies: List<MarketIngredientEntityLegacy>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(marketsIngredients: List<CatalogRecipeMarketIngredientEntity>): Completable

    @Delete
    fun delete(marketIngredientLegacy: MarketIngredientEntityLegacy): Completable
}