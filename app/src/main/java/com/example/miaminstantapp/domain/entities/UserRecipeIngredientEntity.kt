package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.UserRecipeIngredientEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserRecipeIngredientEntity(
    @PrimaryKey
    val id: Int,

    val name: String,

    val volumeUnitId: Int,

    val quantity: Int,

    val recipeId: Int

) {
    companion object {
        const val TABLE_NAME = "user_recipe_ingredients"
    }
}