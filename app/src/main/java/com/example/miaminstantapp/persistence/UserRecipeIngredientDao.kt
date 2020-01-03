package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import com.example.miaminstantapp.domain.entities.RecipeUserIngredientEntity
import io.reactivex.Completable

@Dao
interface UserRecipeIngredientDao {
    @Insert
    fun insertAll(ingredients: List<RecipeUserIngredientEntity>): Completable
}