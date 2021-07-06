package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.MarketIngredientDTO
import com.example.miaminstantapp.domain.entities.MarketIngredientEntity
import io.reactivex.Completable

interface IMarketIngredientRepository {
    fun insertAll(marketIngredients: List<MarketIngredientDTO>): Completable
    fun delete(marketIngredient: MarketIngredientEntity): Completable
}