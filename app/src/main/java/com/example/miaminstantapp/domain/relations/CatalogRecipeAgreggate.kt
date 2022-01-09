package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.*

data class CatalogRecipeAgreggate(
    @Embedded
    val recipe: CatalogRecipeEntity,

    @Relation(parentColumn = "id", entityColumn = "recipeId", entity = CatalogRecipeUserIngredientEntity::class)
    val userIngredients: List<CatalogRecipeUserIngredientRelations>,

    @Relation(parentColumn = "id", entityColumn = "recipeId", entity = CatalogRecipeMarketIngredientEntity::class)
    val marketIngredients: List<MarketIngredientRelations>
) {
    fun toRecipeBookRecipe() = RecipeBookRecipeEntity(
        name = recipe.title,
        content = recipe.content,
        servings = 4,
        preparingMinutes = recipe.preparingMinutes,
        cookingMinutes = recipe.cookingMinutes,
        totalMinutes = recipe.totalMinutes,
        imageUrl = recipe.imageUrl
    )
}