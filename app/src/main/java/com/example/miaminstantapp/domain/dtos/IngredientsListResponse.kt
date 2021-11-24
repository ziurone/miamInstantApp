package com.example.miaminstantapp.domain.dtos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IngredientsListResponse(
    val ingredients: List<Ingredient>
)