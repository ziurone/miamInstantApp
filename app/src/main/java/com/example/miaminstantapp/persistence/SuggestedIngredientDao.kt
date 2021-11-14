package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.miaminstantapp.domain.actions.SuggestedIngredientWithVolumeUnits
import com.example.miaminstantapp.domain.entities.ExcludedSuggestedIngredientsEntity
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
    fun addSuggestedIngredient(suggestedIngredientEntity: SuggestedIngredientEntity): Completable

    @Transaction
    @Query("SELECT * FROM " + SuggestedIngredientEntity.TABLE_NAME)
    fun fetchAll(): Single<List<SuggestedIngredientWithVolumeUnits>>

    @Query("SELECT COUNT(ingredientId) FROM " + SuggestedIngredientEntity.TABLE_NAME)
    fun countAll(): Single<Int>

    @Insert
    fun addExcludedSuggestedIngredient(excludedSuggestedIngredientsEntity: ExcludedSuggestedIngredientsEntity): Completable

    @Insert
    fun addAllExcludedSuggestedIngredient(excludedSuggestedIngredients: List<ExcludedSuggestedIngredientsEntity>): Completable

    @Query("SELECT * FROM " + ExcludedSuggestedIngredientsEntity.TABLE_NAME)
    fun fetchAllExcludedSuggestedIngredients(): Single<List<ExcludedSuggestedIngredientsEntity>>
}