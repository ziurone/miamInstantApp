package com.example.miaminstantapp.domain.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UserIngredientWithVolumeUnits(
    @Embedded
    val ingredient: UserIngredientEntity,

    @Relation(
        parentColumn = UserIngredientVolumeUnitRelation.INGREDIENT_ID_COLUMN_NAME,
        entityColumn = UserIngredientVolumeUnitRelation.VOLUME_UNIT_ID_COLUMN_NAME,
        associateBy = Junction(UserIngredientVolumeUnitRelation::class)
    )
    val volumeUnits: List<VolumeUnitEntity>
)