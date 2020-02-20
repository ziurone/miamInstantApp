package com.example.miaminstantapp.persistence

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class UserMoneySharedPreferences @Inject constructor (
    sharedPreferences: SharedPreferences
) : BaseSharedPreferencesManager(sharedPreferences) {

    private val subject = BehaviorSubject.createDefault(sharedPreferences)

    companion object {
        const val USER_MONEY_KEY = "User.MoneyKey"
    }

    fun setUserMoney(money: Int): Completable = subject
        .firstOrError()
        .editSharedPreferences {
            putInt(USER_MONEY_KEY, money)
        }

    fun getUserMoney(): Single<Int> = Single.just(sharedPreferences.getInt(USER_MONEY_KEY, 0))

}