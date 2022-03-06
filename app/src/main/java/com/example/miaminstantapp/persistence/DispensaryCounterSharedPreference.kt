package com.example.miaminstantapp.persistence

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class DispensaryCounterSharedPreference @Inject constructor (
    sharedPreferences: SharedPreferences
) : BaseSharedPreferencesManager(sharedPreferences) {

    private val subject = BehaviorSubject.createDefault(sharedPreferences)

    companion object {
        const val DISPENSARY_INGREDIENTS_COUNT = "User.MoneyKey"
    }

    fun ingredientAdded(ingredientsCount: Int): Completable = subject
        .firstOrError()
        .editSharedPreferences {
            putInt(DISPENSARY_INGREDIENTS_COUNT, ingredientsCount)
        }

    fun getCounter(): Single<Int> = Single.just(sharedPreferences.getInt(DISPENSARY_INGREDIENTS_COUNT, 0))

}