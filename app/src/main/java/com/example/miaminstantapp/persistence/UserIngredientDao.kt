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
    fun getUserIngredientWithVolumeUnits(ingredientId: Int): Single<UserIngredientWithVolumeUnits>

    @Transaction
    @Insert
    fun add(ingredient: UserIngredientEntity): Completable

    @Query("UPDATE " + UserIngredientEntity.TABLE_NAME + " SET volumeUnitId = :volumeUnitId, volumeUnitQuantity = :volumeUnitQuantity WHERE ingredientId = :ingredientId")
    fun update(ingredientId: Int, volumeUnitId: Int, volumeUnitQuantity: Int): Completable

    @Insert
    fun addIngredientVolumeUnits(ingredientVolumeUnitsRelations: List<UserIngredientVolumeUnitRelation>): Completable

    @Transaction
    @Query("SELECT * FROM " + UserIngredientEntity.TABLE_NAME)
    fun fetchAll(): Single<List<UserIngredientEntity>>

    @Delete
    fun delete(ingredient: UserIngredientEntity): Completable

}