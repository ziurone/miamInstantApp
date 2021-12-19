package com.example.miaminstantapp.domain.dtos

import com.example.miaminstantapp.domain.entities.CatalogRecipeEntityLegacy

data class MarketRecipesListResponse(
    val recipeLegacies: List<CatalogRecipeEntityLegacy>
)