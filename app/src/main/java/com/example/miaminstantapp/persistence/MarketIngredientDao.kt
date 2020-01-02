package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import com.example.miaminstantapp.domain.entities.MarketIngredientEntity
import io.reactivex.Completable

@Dao
interface MarketIngredientDao {
    @Insert
    fun insertAll(marketsIngredients: List<MarketIngredientEntity>): Completable
}