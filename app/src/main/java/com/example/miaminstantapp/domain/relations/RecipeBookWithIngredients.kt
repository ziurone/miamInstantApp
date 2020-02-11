package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeIngredientEntity

data class RecipeBookWithIngredients(
    @Embedded
    val recipe: RecipeBookRecipeEntity,

    @Relation(parentColumn = "id", entityColumn = "recipeId")
    val ingredients: RecipeBookRecipeIngredientEntity
)