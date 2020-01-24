package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeIngredientEntity
import io.reactivex.Completable

@Dao
interface RecipeBookRecipeIngredientDao {

    @Insert
    fun insertAll(ingredients: List<RecipeBookRecipeIngredientEntity>): Completable
}