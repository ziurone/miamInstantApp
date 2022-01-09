package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.CatalogRecipeMarketIngredientEntity
import com.example.miaminstantapp.domain.entities.CatalogRecipeUserIngredientEntity
import com.example.miaminstantapp.domain.entities.MarketIngredientEntityLegacy
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity

data class MarketIngredientRelations(
    @Embedded
    val marketIngredient: CatalogRecipeMarketIngredientEntity,

    @Relation(parentColumn = "volumeUnitId", entityColumn = "volumeUnitId", entity = VolumeUnitEntity::class)
    val volumeUnit: VolumeUnitEntity

)