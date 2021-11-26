package com.example.miaminstantapp.persistence

import androidx.room.*
import com.example.miaminstantapp.domain.actions.SuggestedIngredientWithVolumeUnits
import com.example.miaminstantapp.domain.entities.ExcludedSuggestedIngredientEntity
import com.example.miaminstantapp.domain.entities.SuggestedIngredientEntity
import com.example.miaminstantapp.domain.entities.SuggestedIngredientVolumeUnitRelation
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SuggestedIngredientDao {

    @Insert
    fun addSuggestedIngredientsVolumeUnits(suggestedIngredientVolumeUnitRelation: SuggestedIngredientVolumeUnitRelation): Completable

    @Transaction
    @Insert
    fun addAllSuggestedIngredientsVolumeUnits(suggestedIngredientsVolumeUnitRelation: List<SuggestedIngredientVolumeUnitRelation>): Completable

    @Transaction
    @Insert
    fun addSuggestedIngredient(suggestedIngredientEntity: SuggestedIngredientEntity): Completable

    @Transaction
    @Insert
    fun addAllSuggestedIngredient(suggestedIngredientsEntity: List<SuggestedIngredientEntity>): Completable

    @Transaction
    @Query("SELECT * FROM " + SuggestedIngredientEntity.TABLE_NAME)
    fun fetchAll(): Single<List<SuggestedIngredientWithVolumeUnits>>

    @Query("SELECT COUNT(ingredientId) FROM " + SuggestedIngredientEntity.TABLE_NAME)
    fun countAll(): Single<Int>

    @Insert
    fun addExcludedSuggestedIngredient(excludedSuggestedIngredientEntity: ExcludedSuggestedIngredientEntity): Completable

    @Delete
    fun removeSuggestedIngredient(suggestedIngredientEntity: SuggestedIngredientEntity): Completable

    @Insert
    fun addAllExcludedSuggestedIngredient(excludedSuggestedIngredients: List<ExcludedSuggestedIngredientEntity>): Completable

    @Query("SELECT * FROM " + ExcludedSuggestedIngredientEntity.TABLE_NAME)
    fun fetchAllExcludedSuggestedIngredients(): Single<List<ExcludedSuggestedIngredientEntity>>
}