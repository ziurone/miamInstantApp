package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserIngredientVolumeUnitRelation(
    val ingredientId: Int,
    val volumeUnitId: Int
) {

    companion object {
        const val INGREDIENT_ID_COLUMN_NAME = "ingredientId"
        const val VOLUME_UNIT_ID_COLUMN_NAME = "volumeUnitId"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}