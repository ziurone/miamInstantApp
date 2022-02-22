package com.example.miaminstantapp.domain.repositories

import io.reactivex.Completable

interface ICatalogRecipeTotalTimeMinutesRepository {
    fun setTotalMinutes(totalMinutes: Int): Completable
}