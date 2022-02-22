package com.example.miaminstantapp.persistence

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class CatalogRecipeTotalTimeFilterPreference @Inject constructor (
    sharedPreferences: SharedPreferences
) : BaseSharedPreferencesManager(sharedPreferences) {

    private val subject = BehaviorSubject.createDefault(sharedPreferences)

    companion object {
        const val CATALOG_RECIPE_TOTAL_TIME_FILTER = "CatalogRecipe.TotalTimeFilter"
    }

    fun setTotalTimeFilter(totalMinutes: Int): Completable = subject
        .firstOrError()
        .editSharedPreferences {
            putInt(CATALOG_RECIPE_TOTAL_TIME_FILTER, totalMinutes)
        }

    fun getTotalTimeFilter(): Single<Int> = Single.just(sharedPreferences.getInt(CATALOG_RECIPE_TOTAL_TIME_FILTER, 0))

}