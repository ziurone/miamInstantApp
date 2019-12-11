package com.example.miaminstantapp.domain.dtos

data class Ingredient(
    val id: Int,
    val name: String,
    val defaultVolumeUnitId: Int,
    val defaultVolumeUnitQuantity: Int,
    val volumeUnitIds: List<Int>
)