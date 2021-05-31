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
    val branchId: Int,
    val recipeId: Int
) {
    companion object {
        const val TABLE_NAME = "marketIngredients"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun MarketIngredientEntity.toRecipeBookIngredient(recipe: Int): RecipeBookRecipeIngredientEntity = RecipeBookRecipeIngredientEntity(
    name = ingredientName,
    quantity = usedQuantity,
    recipeId = recipe
)

fun MarketIngredientEntity.toShopArticle(): ShopArticleEntity {
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