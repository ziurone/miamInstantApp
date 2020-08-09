package com.example.miaminstantapp.persistence

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class IsFirstTimeInAppPreference @Inject constructor(
    sharedPreferences: SharedPreferences
): BaseSharedPreferencesManager(sharedPreferences) {

    private val subject = BehaviorSubject.createDefault(sharedPreferences)

    companion object {
        const val USER_IS_FIRST_TIME_IN_APP = "User.MoneyKey"
    }

    fun setIsFirstTimeInApp(isFirstTime: Boolean): Completable = subject
        .firstOrError()
        .editSharedPreferences {
            putBoolean(USER_IS_FIRST_TIME_IN_APP, isFirstTime)
        }

    fun isFirstTime(): Single<Boolean> = Single.just(sharedPreferences.getBoolean(USER_IS_FIRST_TIME_IN_APP, true))
}