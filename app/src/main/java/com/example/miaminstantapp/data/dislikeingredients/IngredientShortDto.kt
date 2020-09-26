package com.example.miaminstantapp.data.dislikeingredients

data class IngredientShortDto(
    val id: Int,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}