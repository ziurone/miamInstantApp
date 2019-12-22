package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.entities.UserIngredientWithVolumeUnits

@Dao
interface UserIngredientDao {

    @Transaction
    @Query("SELECT * FROM " + UserIngredientEntity.TABLE_NAME)
    fun getUserIngredientsWithVolumeUnits(): List<UserIngredientWithVolumeUnits>

}