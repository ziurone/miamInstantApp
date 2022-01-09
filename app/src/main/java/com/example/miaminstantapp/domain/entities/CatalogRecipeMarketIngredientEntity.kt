package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.CatalogRecipeMarketIngredientEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CatalogRecipeMarketIngredientEntity(
    @PrimaryKey
    val ingredientId: Int,
    val name: String,
    val volumeUnitId: Int,
    val usedQuantity: Int,
    val recipeId: Int
) {
    companion object {
        const val TABLE_NAME = "catalogRecipeMarketIngredients"
    }

    fun toShoppingArticle() = ShoppingListArticleEntity(
        ingredientId = ingredientId,
        ingredientName = name,
        volumeUnitId = volumeUnitId,
        quantity = usedQuantity,
        recipeId = recipeId
    )

    fun toRecipeBookIngredient() = RecipeBookRecipeIngredientEntity(
        name = name,
        volumeUnitId = volumeUnitId,
        quantity = usedQuantity,
        recipeId = recipeId
    )

}