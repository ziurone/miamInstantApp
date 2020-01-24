package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.RecipeBookRecipeIngredientEntity
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity

data class UserRecipeIngredientWithVolumeUnit(

    @Embedded
    val recipeBookRecipeIngredient: RecipeBookRecipeIngredientEntity,

    @Relation(parentColumn = "volumeUnitId", entityColumn = "volumeUnitId")
    val volumeUnit: VolumeUnitEntity
)