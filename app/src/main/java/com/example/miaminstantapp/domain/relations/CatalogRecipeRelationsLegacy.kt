package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.MarketIngredientEntityLegacy
import com.example.miaminstantapp.domain.entities.CatalogRecipeEntityLegacy
import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity

data class CatalogRecipeRelationsLegacy (

    @Embedded
    val recipeLegacy: CatalogRecipeEntityLegacy,

    @Relation(parentColumn = "id", entityColumn = "recipeId", entity = CatalogRecipeUserIngredientEntity::class)
    val userIngredients: List<CatalogRecipeUserIngredientRelations>,

    @Relation(parentColumn = "id", entityColumn = "recipeId", entity = MarketIngredientEntityLegacy::class)
    val marketIngredients: List<MarketIngredientRelations>

)

fun CatalogRecipeRelationsLegacy.toRecipeBookRecipe(): RecipeBookRecipeEntity = RecipeBookRecipeEntity(
    name = recipeLegacy.title,
    content = recipeLegacy.content,
    link = recipeLegacy.link,
    servings = recipeLegacy.servings,
    preparingMinutes = recipeLegacy.preparingMinutes,
    cookingMinutes = recipeLegacy.cookingMinutes,
    totalMinutes = recipeLegacy.totalMinutes,
    siteId = recipeLegacy.siteId,
    imageUrl = recipeLegacy.imageUrl
)