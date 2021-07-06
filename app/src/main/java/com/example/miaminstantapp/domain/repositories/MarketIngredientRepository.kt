package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.MarketIngredientDTO
import com.example.miaminstantapp.domain.dtos.toMarketIngredientEntity
import com.example.miaminstantapp.domain.entities.MarketIngredientEntity
import com.example.miaminstantapp.persistence.MarketIngredientDao
import io.reactivex.Completable
import javax.inject.Inject

class MarketIngredientRepository @Inject constructor(
    private val marketIngredientDao: MarketIngredientDao
): IMarketIngredientRepository {
    override fun insertAll(marketIngredients: List<MarketIngredientDTO>): Completable  = marketIngredientDao.insertAll(
        marketIngredients.map {
            it.toMarketIngredientEntity()
        }
    )

    override fun delete(marketIngredient: MarketIngredientEntity) = marketIngredientDao.delete(marketIngredient)
}