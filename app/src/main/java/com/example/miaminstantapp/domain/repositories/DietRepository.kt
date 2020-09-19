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

    override fun getUserDiets() {
        TODO("Not yet implemented")
    }
}