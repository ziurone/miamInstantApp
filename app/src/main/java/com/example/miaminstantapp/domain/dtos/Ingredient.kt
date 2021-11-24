package com.example.miaminstantapp.domain.dtos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ingredient(
    val id: Int,
    val name: String,
    val defaultVolumeUnitId: Int,
    val defaultVolumeUnitQuantity: Int,
    val volumeUnitsIds: List<Int>
)