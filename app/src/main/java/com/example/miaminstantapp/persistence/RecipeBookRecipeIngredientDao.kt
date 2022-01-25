package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeIngredientEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface RecipeBookRecipeIngredientDao {

    @Insert
    fun insertAll(ingredients: List<RecipeBookRecipeIngredientEntity>): Completable

    @Query("SELECT * FROM " + RecipeBookRecipeIngredientEntity.TABLE_NAME + " WHERE recipeId = :recipeId")
    fun fetch(recipeId: Int): Single<List<RecipeBookRecipeIngredientEntity>>
}