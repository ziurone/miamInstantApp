package com.example.miaminstantapp.data.dislikeingredients

import com.example.miaminstantapp.domain.entities.ExcludedIngredientEntity

data class IngredientShortDto(
    val id: Int,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}

fun IngredientShortDto.toExcludedIngredientEntity() = ExcludedIngredientEntity(
    name = name
)