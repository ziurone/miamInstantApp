package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.dtos.MarketIngredientDTOLegacy
import com.example.miaminstantapp.domain.dtos.toMarketIngredientEntity
import com.example.miaminstantapp.domain.entities.MarketIngredientEntityLegacy
import com.example.miaminstantapp.persistence.MarketIngredientDao
import io.reactivex.Completable
import javax.inject.Inject

class MarketIngredientRepository @Inject constructor(
    private val marketIngredientDao: MarketIngredientDao
): IMarketIngredientRepository {
    override fun insertAll(marketIngredientLegacies: List<MarketIngredientDTOLegacy>): Completable  = marketIngredientDao.insertAllLegacy(
        marketIngredientLegacies.map {
            it.toMarketIngredientEntity()
        }
    )

    override fun delete(marketIngredientLegacy: MarketIngredientEntityLegacy) = marketIngredientDao.delete(marketIngredientLegacy)
}