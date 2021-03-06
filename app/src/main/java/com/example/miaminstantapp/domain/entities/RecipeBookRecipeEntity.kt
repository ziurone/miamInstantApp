package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class RecipeBookRecipeEntity(

    val name: String,

    val content: String,

    val servings: Int,

    val preparingMinutes: Int,

    val cookingMinutes: Int,

    val totalMinutes: Int,

    val imageUrl: String?,

    val hasBeenPrepared: Boolean = false,

    val recipeId: Int
) {
    companion object {
        const val TABLE_NAME = "recipeBookToDoRecipes"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}