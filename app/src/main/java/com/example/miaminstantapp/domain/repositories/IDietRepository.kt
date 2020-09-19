package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.enums.Diet
import io.reactivex.Completable

interface IDietRepository {
    fun getDiets() : List<Diet>
    fun addDiet(diet: Diet): Completable
    fun getUserDiets()
}