package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.ShopArticleEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ShopArticleEntity(
    val branchId: Int,
    val branchArticleId: Int,
    val articlesQuantity: Int,
    val articleName: String,
    val unitPrice: Float,
    val totalPrice: Float,
    val recipeId: Int
)

{
    companion object {
        const val TABLE_NAME = "shop_articles"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}