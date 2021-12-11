package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.MarketIngredientEntityLegacy.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MarketIngredientEntityLegacy (
    val ingredientId: Int,
    val ingredientName: String,
    val volumeUnitId: Int,
    val volumeUnitQuantity: Float,
    val branchArticleId: Int,
    val usedQuantity: Int,
    val articleUnitQuantity: Int,
    val articleTotalQuantity: Int,
    val articlesQuantity: Int,
    val articleName: String,
    val unitPrice: Float,
    val totalPrice: Float,
    val branchId: Int,
    val shopName: String,
    val recipeId: Int
) {
    companion object {
        const val TABLE_NAME = "marketIngredients"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun MarketIngredientEntityLegacy.toRecipeBookIngredient(recipe: Int): RecipeBookRecipeIngredientEntity = RecipeBookRecipeIngredientEntity(
    name = ingredientName,
    quantity = usedQuantity,
    recipeId = recipe
)

fun MarketIngredientEntityLegacy.toShopArticle(): ShopArticleEntity {
    return ShopArticleEntity(
        branchId = branchId,
        branchArticleId = branchArticleId,
        articlesQuantity = articlesQuantity,
        articleName = articleName,
        unitPrice = unitPrice,
        totalPrice = totalPrice,
        recipeId = recipeId
    )
}