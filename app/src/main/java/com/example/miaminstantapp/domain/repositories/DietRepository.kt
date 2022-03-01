package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.enums.Diet
import com.example.miaminstantapp.persistence.DietDao
import io.reactivex.Completable
import javax.inject.Inject

class DietRepository @Inject constructor(
    private val dietDao: DietDao
): IDietRepository {
    override fun getDiets(): List<Diet> = Diet.values().toList()

    override fun addDiet(diet: Diet): Completable = dietDao.insert(diet.toEntity())

    override fun addDiets(diets: List<Diet>): Completable {
        return dietDao
            .removeAll()
            .andThen(dietDao.insertAll(diets.map {it.toEntity()}))
    }

    override fun getUserDiets() = dietDao.fetchAll()

    override fun removeDiet(diet: Diet): Completable = dietDao.remove(diet.name)
}