package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class RecipeBookRecipeEntity(

    val name: String,

    val content: String,

    val link: String,

    val servings: Int,

    val preparingMinutes: Int,

    val cookingMinutes: Int,

    val totalMinutes: Int,

    val siteId: Int,

    val imageUrl: String
) {
    companion object {
        const val TABLE_NAME = "user_recipes"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}