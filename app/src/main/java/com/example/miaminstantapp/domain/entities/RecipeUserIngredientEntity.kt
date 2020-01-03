package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.RecipeUserIngredientEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class RecipeUserIngredientEntity(
    val ingredientId: Int,
    val usedQuantity: Int,
    val recipeId: Int
) {
    companion object {
        const val TABLE_NAME = "recipe_user_ingredients"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}