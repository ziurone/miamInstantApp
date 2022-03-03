package com.example.miaminstantapp.domain.repositories

import io.reactivex.Completable
import io.reactivex.Single

interface ICatalogRecipeTotalTimeMinutesRepository {
    fun setTotalMinutes(totalMinutes: Int): Completable
    fun getTotalMinutes(): Single<Int>
}