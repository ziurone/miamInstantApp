package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.miaminstantapp.domain.entities.SuggestedIngredientEntity
import com.example.miaminstantapp.domain.entities.SuggestedIngredientVolumeUnitRelation
import io.reactivex.Completable

@Dao
interface SuggestedIngredientDao {

    @Insert
    fun addSuggestedIngredientsVolumeUnits(suggestedIngredientVolumeUnitRelation: SuggestedIngredientVolumeUnitRelation): Completable

    @Transaction
    @Insert
    fun addSuggestedIngredient(suggestedIngredientEntity: SuggestedIngredientEntity): Completable
}