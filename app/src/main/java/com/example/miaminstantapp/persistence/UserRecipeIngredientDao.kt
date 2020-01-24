package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import com.example.miaminstantapp.domain.entities.UserRecipeIngredientEntity
import io.reactivex.Completable

@Dao
interface UserRecipeIngredientDao {

    @Insert
    fun insertAll(ingredients: List<UserRecipeIngredientEntity>): Completable
}