package com.example.miaminstantapp.domain.dtos

import com.example.miaminstantapp.domain.entities.MarketRecipeEntity

data class MarketRecipesListResponse(
    val recipes: List<MarketRecipeEntity>
)