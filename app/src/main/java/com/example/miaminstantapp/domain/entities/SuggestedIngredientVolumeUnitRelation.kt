package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.SuggestedIngredientVolumeUnitRelation.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class SuggestedIngredientVolumeUnitRelation (
    val ingredientId: Int,
    val volumeUnitId: Int
    ) {

    companion object {
        const val INGREDIENT_ID_COLUMN_NAME = "ingredientId"
        const val VOLUME_UNIT_ID_COLUMN_NAME = "volumeUnitId"
        const val TABLE_NAME = "suggestedIngredientVolumeUnitRelations"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}