package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.SuggestedIngredientEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class SuggestedIngredientEntity(
    @PrimaryKey
    val ingredientId: Int,
    val name: String,
    val volumeUnitId: Int,
    val volumeUnitQuantity: Int
) {

    companion object {
        const val TABLE_NAME = "suggestedIngredients"
    }
}