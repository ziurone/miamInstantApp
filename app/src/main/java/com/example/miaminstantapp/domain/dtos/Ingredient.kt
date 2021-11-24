package com.example.miaminstantapp.domain.dtos

import com.example.miaminstantapp.domain.entities.SuggestedIngredientEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ingredient(
    val id: Int,
    val name: String,
    val defaultVolumeUnitId: Int,
    val defaultVolumeUnitQuantity: Int,
    val volumeUnitsIds: List<Int>
) {
    fun toSuggestedIngredientEntity() = SuggestedIngredientEntity(
        ingredientId = id,
        name = name,
        volumeUnitId = defaultVolumeUnitId,
        volumeUnitQuantity = defaultVolumeUnitQuantity
    )
}