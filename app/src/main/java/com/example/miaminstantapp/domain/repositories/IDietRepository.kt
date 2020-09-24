package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.DietEntity
import com.example.miaminstantapp.domain.enums.Diet
import io.reactivex.Completable
import io.reactivex.Single

interface IDietRepository {
    fun getDiets() : List<Diet>
    fun addDiet(diet: Diet): Completable
    fun removeDiet(diet: Diet): Completable
    fun getUserDiets(): Single<List<DietEntity>>
}