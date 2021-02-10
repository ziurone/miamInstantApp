package com.example.miaminstantapp.domain.dtos

import com.example.miaminstantapp.domain.entities.CatalogRecipeEntity

data class MarketRecipesListResponse(
    val recipes: List<CatalogRecipeEntity>
)