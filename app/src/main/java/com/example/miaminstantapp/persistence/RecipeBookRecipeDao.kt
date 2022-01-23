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

    @Query("SELECT * FROM " + RecipeBookRecipeEntity.TABLE_NAME + " ORDER by id DESC")
    fun fetchRecipesDesc(): Single<List<RecipeBookRecipeEntity>>

    @Query("SELECT * FROM " + RecipeBookRecipeEntity.TABLE_NAME + " WHERE id = :id")
    fun fetchRecipeById(id: Int): Single<RecipeBookRecipeEntity>
}