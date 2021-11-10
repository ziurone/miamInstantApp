package com.example.miaminstantapp.dtos.ingredient

import com.example.miaminstantapp.domain.dtos.Ingredient

data class SuggestedIngredientsResponse(
    val ingredients: List<Ingredient>
)