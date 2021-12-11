package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.MarketIngredientDTOLegacy
import com.example.miaminstantapp.domain.entities.MarketIngredientEntityLegacy
import io.reactivex.Completable

interface IMarketIngredientRepository {
    fun insertAll(marketIngredientLegacies: List<MarketIngredientDTOLegacy>): Completable
    fun delete(marketIngredientLegacy: MarketIngredientEntityLegacy): Completable
}