package com.example.miaminstantapp.persistence

import androidx.room.*
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

    @Query("DELETE FROM " + CatalogRecipeMarketIngredientEntity.TABLE_NAME)
    fun clean(): Completable
}