package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.data.dislikeingredients.IngredientShortDto
import com.example.miaminstantapp.data.dislikeingredients.toExcludedIngredientEntity
import com.example.miaminstantapp.domain.entities.ExcludedIngredientEntity
import com.example.miaminstantapp.persistence.ExcludedIngredientDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ExcludedIngredientRepository @Inject constructor(
    private val excludedIngredientDao: ExcludedIngredientDao
): IExcludedIngredientRepository {
    override fun add(ingredient: IngredientShortDto): Completable = excludedIngredientDao.insert(ingredient.toExcludedIngredientEntity())
    override fun fetchAll(): Single<List<ExcludedIngredientEntity>> = excludedIngredientDao.fetchAll()
    override fun remove(ingredientId: Int): Completable = excludedIngredientDao.remove(ingredientId)
}