package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.MarketIngredientEntity
import com.example.miaminstantapp.domain.entities.MarketRecipeEntity
import com.example.miaminstantapp.domain.entities.DoableRecipeUserIngredient

data class DoableRecipe (

    @Embedded
    val recipe: MarketRecipeEntity,

    @Relation(parentColumn = "id", entityColumn = "recipeId")
    val userIngredients: List<DoableRecipeUserIngredient>,

    @Relation(parentColumn = "id", entityColumn = "recipeId")
    val marketIngredients: List<MarketIngredientEntity>

)