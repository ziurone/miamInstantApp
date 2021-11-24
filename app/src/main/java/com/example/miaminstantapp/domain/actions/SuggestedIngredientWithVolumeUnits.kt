package com.example.miaminstantapp.domain.actions

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.entities.SuggestedIngredientEntity
import com.example.miaminstantapp.domain.entities.SuggestedIngredientVolumeUnitRelation
import com.example.miaminstantapp.domain.entities.UserIngredientVolumeUnitRelation
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity

data class SuggestedIngredientWithVolumeUnits (
    @Embedded
    val ingredient: SuggestedIngredientEntity,

    @Relation(
        parentColumn = SuggestedIngredientVolumeUnitRelation.INGREDIENT_ID_COLUMN_NAME,
        entityColumn = SuggestedIngredientVolumeUnitRelation.VOLUME_UNIT_ID_COLUMN_NAME,
        associateBy = Junction(SuggestedIngredientVolumeUnitRelation::class)
    )
    val volumeUnits: List<VolumeUnitEntity>

) {
    fun toIngredient(): Ingredient = Ingredient(
        id = ingredient.ingredientId,
        name = ingredient.name,
        defaultVolumeUnitId = ingredient.volumeUnitId,
        defaultVolumeUnitQuantity = ingredient.volumeUnitQuantity,
        volumeUnitsIds = volumeUnits.map { it.volumeUnitId }
    )
}