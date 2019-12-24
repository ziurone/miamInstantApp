package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.entities.UserIngredientVolumeUnitRelation
import com.example.miaminstantapp.domain.entities.UserIngredientWithVolumeUnits
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserIngredientDao {

    @Transaction
    @Query("SELECT * FROM " + UserIngredientEntity.TABLE_NAME)
    fun getUserIngredientsWithVolumeUnits(): List<UserIngredientWithVolumeUnits>

    @Transaction
    @Insert
    fun add(ingredient: UserIngredientEntity): Completable

    @Insert
    fun addIngredientVolumeUnits(ingredientVolumeUnitsRelations: List<UserIngredientVolumeUnitRelation>): Completable

    @Transaction
    @Query("SELECT * FROM " + UserIngredientEntity.TABLE_NAME)
    fun fetchAll(): Single<List<UserIngredientEntity>>

}