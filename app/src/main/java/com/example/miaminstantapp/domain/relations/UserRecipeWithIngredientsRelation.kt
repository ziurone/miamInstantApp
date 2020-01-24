package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeEntity
import com.example.miaminstantapp.domain.entities.UserRecipeIngredientEntity

data class UserRecipeWithIngredientsRelation(
    @Embedded
    val recipe: RecipeBookRecipeEntity,

    @Relation(parentColumn = "id", entityColumn = "recipeId", entity = UserRecipeIngredientEntity::class)
    val ingredients: UserRecipeIngredientWithVolumeUnit
)