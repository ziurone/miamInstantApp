package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import com.example.miaminstantapp.domain.entities.DoableRecipeUserIngredient
import io.reactivex.Completable

@Dao
interface RecipeUserIngredientDao {
    @Insert
    fun insertAll(ingredients: List<DoableRecipeUserIngredient>): Completable
}