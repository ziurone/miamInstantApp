package com.example.miaminstantapp.persistence

import android.widget.AutoCompleteTextView
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.miaminstantapp.domain.entities.MarketIngredientEntity
import io.reactivex.Completable

@Dao
interface MarketIngredientDao {
    @Insert
    fun insertAll(marketsIngredients: List<MarketIngredientEntity>): Completable

    @Delete
    fun delete(marketIngredient: MarketIngredientEntity): Completable
}