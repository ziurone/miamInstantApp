package com.example.miaminstantapp.persistence

import androidx.room.*
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.entities.UserIngredientVolumeUnitRelation
import com.example.miaminstantapp.domain.relations.UserIngredientWithVolumeUnits
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserIngredientDao {

    @Transaction
    @Query("SELECT * FROM " + UserIngredientEntity.TABLE_NAME)
    fun getUserIngredientsWithVolumeUnits(): Single<List<UserIngredientWithVolumeUnits>>

    @Transaction
    @Query("SELECT * FROM " + UserIngredientEntity.TABLE_NAME + " WHERE ingredientId = :ingredientId ")
    fun getUserIngredientWithVolumeUnits(ingredientId: Int): Single<List<UserIngredientWithVolumeUnits>>

    @Transaction
    @Insert
    fun add(ingredient: UserIngredientEntity): Completable

    @Insert
    fun addIngredientVolumeUnits(ingredientVolumeUnitsRelations: List<UserIngredientVolumeUnitRelation>): Completable

    @Transaction
    @Query("SELECT * FROM " + UserIngredientEntity.TABLE_NAME)
    fun fetchAll(): Single<List<UserIngredientEntity>>

    @Delete
    fun delete(ingredient: UserIngredientEntity): Completable

}