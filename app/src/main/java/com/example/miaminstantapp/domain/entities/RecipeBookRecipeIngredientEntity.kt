package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeIngredientEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class RecipeBookRecipeIngredientEntity(
    val name: String,
    val volumeUnitId: Int,
    val quantity: Int,
    val recipeId: Int

) {
    companion object {
        const val TABLE_NAME = "user_recipe_ingredients"
        
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}