package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import com.example.miaminstantapp.domain.entities.MarketRecipeEntity
import io.reactivex.Completable

@Dao
interface MarketRecipeDao {
    @Insert
    fun insertAll(recipes: List<MarketRecipeEntity>): Completable
}