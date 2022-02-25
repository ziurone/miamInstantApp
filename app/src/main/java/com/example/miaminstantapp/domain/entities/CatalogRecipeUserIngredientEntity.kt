package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity.Companion.TABLE_NAME

/**
 * User ingredients in doableRecipe
 */
@Entity(tableName = TABLE_NAME)
data class CatalogRecipeUserIngredientEntity(
    val ingredientId: Int,
    val ingredientName: String,
    val usedQuantity: Double,
    val volumeUnitId: Int,
    val recipeId: Int
) {
    companion object {
        const val TABLE_NAME = "catalogRecipesUserIngredients"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun CatalogRecipeUserIngredientEntity.toRecipeBookIngredient(recipe: Int) = RecipeBookRecipeIngredientEntity(
    name = ingredientName,
    volumeUnitId = volumeUnitId,
    quantity = usedQuantity,
    recipeId = recipe
)