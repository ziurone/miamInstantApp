package com.example.miaminstantapp.persistence

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class DispensaryCounterSharedPreference @Inject constructor (
    sharedPreferences: SharedPreferences
) : BaseSharedPreferencesManager(sharedPreferences) {

    private val subject = BehaviorSubject.createDefault(sharedPreferences)
    val preferenceLiveData = MutableLiveData<Int>()
    private val counterListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == DISPENSARY_INGREDIENTS_COUNT) preferenceLiveData.value = sharedPreferences.getInt(DISPENSARY_INGREDIENTS_COUNT, 0)
    }

    companion object {
        const val DISPENSARY_INGREDIENTS_COUNT = "User.MoneyKey"
    }

    fun initializeListener() {
        preferenceLiveData.value = sharedPreferences.getInt(DISPENSARY_INGREDIENTS_COUNT, 0)
        sharedPreferences.registerOnSharedPreferenceChangeListener(counterListener)
    }

    fun ingredientAdded(ingredientsCount: Int): Completable = subject
        .firstOrError()
        .editSharedPreferences {
            putInt(DISPENSARY_INGREDIENTS_COUNT, ingredientsCount)
        }

    fun getCounter(): Single<Int> = Single.just(sharedPreferences.getInt(DISPENSARY_INGREDIENTS_COUNT, 0))

}