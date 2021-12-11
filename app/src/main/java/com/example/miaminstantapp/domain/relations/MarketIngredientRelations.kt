package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import com.example.miaminstantapp.domain.entities.MarketIngredientEntityLegacy
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity

data class MarketIngredientRelations(
    @Embedded
    val marketIngredientLegacy: MarketIngredientEntityLegacy,

    @Relation(parentColumn = "volumeUnitId", entityColumn = "volumeUnitId", entity = VolumeUnitEntity::class)
    val volumeUnit: VolumeUnitEntity

)

fun MarketIngredientRelations.toRecipeUserIngredient() = CatalogRecipeUserIngredientEntity(
    ingredientId = marketIngredientLegacy.ingredientId,
    ingredientName = marketIngredientLegacy.ingredientName,
    usedQuantity = marketIngredientLegacy.usedQuantity,
    recipeId = marketIngredientLegacy.recipeId,
    volumeUnitId = volumeUnit.volumeUnitId,
    volumeUnitQuantity = marketIngredientLegacy.volumeUnitQuantity
)
