package com.example.miaminstantapp.domain.actions

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.SuggestedIngredientEntity
import com.example.miaminstantapp.domain.entities.SuggestedIngredientVolumeUnitRelation
import com.example.miaminstantapp.domain.entities.UserIngredientVolumeUnitRelation
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity

data class SuggestedIngredientWithVolumeUnits (
    @Embedded
    val ingredient: SuggestedIngredientEntity,

    @Relation(
        parentColumn = UserIngredientVolumeUnitRelation.INGREDIENT_ID_COLUMN_NAME,
        entityColumn = UserIngredientVolumeUnitRelation.VOLUME_UNIT_ID_COLUMN_NAME,
        associateBy = Junction(SuggestedIngredientVolumeUnitRelation::class)
    )
    val volumeUnits: List<VolumeUnitEntity>

)