package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.ExcludedIngredientEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ExcludedIngredientEntity(
    val ingredientId: Int,
    val name: String
) {

    companion object {
        const val TABLE_NAME = "excluded_ingredients"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}