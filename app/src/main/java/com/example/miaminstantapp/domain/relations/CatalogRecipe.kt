package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.MarketIngredientEntity
import com.example.miaminstantapp.domain.entities.CatalogRecipeEntity
import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity

data class CatalogRecipe (

    @Embedded
    val recipe: CatalogRecipeEntity,

    @Relation(parentColumn = "id", entityColumn = "recipeId")
    val userIngredients: List<CatalogRecipeUserIngredientEntity>,

    @Relation(parentColumn = "id", entityColumn = "recipeId")
    val marketIngredients: List<MarketIngredientEntity>

)

fun CatalogRecipe.toRecipeBookRecipe(): RecipeBookRecipeEntity = RecipeBookRecipeEntity(
    name = recipe.title,
    content = recipe.content,
    link = recipe.link,
    servings = recipe.servings,
    preparingMinutes = recipe.preparingMinutes,
    cookingMinutes = recipe.cookingMinutes,
    totalMinutes = recipe.totalMinutes,
    siteId = recipe.siteId,
    imageUrl = recipe.imageUrl
)