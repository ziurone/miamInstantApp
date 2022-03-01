package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.ShoppingListArticleEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ShoppingListArticleEntity(
    @PrimaryKey
    val ingredientId: Int,
    val ingredientName: String,
    val volumeUnitId: Int,
    val quantity: Double,
    val recipeId: Int
){
    companion object {
        const val TABLE_NAME = "shoppingArticles"
    }
}