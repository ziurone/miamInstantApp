package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.MarketIngredientEntity
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity

data class MarketIngredientRelations(
    @Embedded
    val marketIngredient: MarketIngredientEntity,

    @Relation(parentColumn = "volumeUnitId", entityColumn = "volumeUnitId", entity = VolumeUnitEntity::class)
    val volumeUnit: VolumeUnitEntity
)