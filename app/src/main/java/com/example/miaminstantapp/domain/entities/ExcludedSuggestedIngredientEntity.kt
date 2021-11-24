package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.ExcludedSuggestedIngredientEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ExcludedSuggestedIngredientEntity(
    @PrimaryKey
    val id: Int
) {
    companion object {
        const val TABLE_NAME = "excludedSuggestedIngredients"
    }
}