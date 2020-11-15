package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.MarketRecipeEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MarketRecipeEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val content: String,
    val link: String,
    val servings: Int,
    val price: Float,
    val preparingMinutes: Int,
    val cookingMinutes: Int,
    val totalMinutes: Int,
    val siteId: Int,
    val imageUrl: String
) {
    companion object {
        const val TABLE_NAME = "marketRecipes"
    }
}