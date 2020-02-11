package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.DoableRecipeUserIngredient.Companion.TABLE_NAME

/**
 * User ingredients in doableRecipe
 */
@Entity(tableName = TABLE_NAME)
data class DoableRecipeUserIngredient(
    val ingredientId: Int,
    val ingredientName: String,
    val usedQuantity: Int,
    val recipeId: Int
) {
    companion object {
        const val TABLE_NAME = "doable_recipe_user_ingredients"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun DoableRecipeUserIngredient.toRecipeBookIngredient(recipe: Int) = RecipeBookRecipeIngredientEntity(
    name = ingredientName,
    quantity = usedQuantity,
    recipeId = recipe
)