package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.CatalogRecipeMarketIngredientEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CatalogRecipeMarketIngredientEntity(
    @PrimaryKey
    val ingredientId: Int,
    val usedQuantity: Int,
    val usedVolumeUnitQuantityId: Int,
    val recipeId: Int
) {
    companion object {
        const val TABLE_NAME = "catalogRecipeMarketIngredients"
    }
}