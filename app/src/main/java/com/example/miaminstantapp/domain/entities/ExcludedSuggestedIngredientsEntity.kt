package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.ExcludedSuggestedIngredientsEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ExcludedSuggestedIngredientsEntity(
    @PrimaryKey
    val id: Int
) {
    companion object {
        const val TABLE_NAME = "excludedSuggestedIngredients"
    }
}