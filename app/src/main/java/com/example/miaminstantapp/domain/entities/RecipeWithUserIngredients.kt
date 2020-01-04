package com.example.miaminstantapp.domain.entities

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithUserIngredients (

    @Embedded
    val recipe: MarketRecipeEntity,

    @Relation(parentColumn = "id", entityColumn = "recipeId")
    val userIngredients: List<RecipeUserIngredientEntity>
)