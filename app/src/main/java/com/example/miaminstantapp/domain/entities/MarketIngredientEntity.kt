package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.MarketIngredientEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MarketIngredientEntity (
    val ingredientId: Int,
    val ingredientName: String,
    val branchArticleId: Int,
    val usedQuantity: Int,
    val articleUnitQuantity: Int,
    val articleTotalQuantity: Int,
    val articlesQuantity: Int,
    val articleName: String,
    val unitPrice: Float,
    val totalPrice: Float,
    val branchId: Int
) {
    companion object {
        const val TABLE_NAME = "marketIngredients"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}