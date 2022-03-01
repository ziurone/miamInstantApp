package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface RecipeBookRecipeDao {
    @Insert
    fun addRecipeToRecipeBook(recipe: RecipeBookRecipeEntity): Completable

    @Query("SELECT * FROM " + RecipeBookRecipeEntity.TABLE_NAME + " WHERE hasBeenPrepared = :hasBeenPrepared ORDER by id DESC")
    fun fetchRecipesDesc(hasBeenPrepared: Boolean = false): Single<List<RecipeBookRecipeEntity>>

    @Query("SELECT * FROM " + RecipeBookRecipeEntity.TABLE_NAME + " WHERE id = :id")
    fun fetchRecipeById(id: Int): Single<RecipeBookRecipeEntity>

    @Query("UPDATE " + RecipeBookRecipeEntity.TABLE_NAME + " SET hasBeenPrepared = :prepared WHERE recipeId = :recipeId")
    fun setRecipeAsMade(recipeId: Int, prepared: Boolean = true): Completable

    @Query("SELECT id FROM " + RecipeBookRecipeEntity.TABLE_NAME)
    fun fetchRecipesIds(): Single<List<Int>>
}