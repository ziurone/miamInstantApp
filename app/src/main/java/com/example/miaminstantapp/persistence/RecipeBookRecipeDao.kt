package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import io.reactivex.Completable

@Dao
interface RecipeBookRecipeDao {
    @Insert
    fun addRecipeToRecipeBook(recipe: RecipeBookRecipeEntity): Completable
}