package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.persistence.UserMoneySharedPreferences
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class UserMoneyRepository @Inject constructor(
    private val userMoneySharedPreferences: UserMoneySharedPreferences
): IUserMoneyRepository {
    override fun setUserMoney(money: Int): Completable = userMoneySharedPreferences.setUserMoney(money)
    override fun getUserMoney(): Single<Int> = userMoneySharedPreferences.getUserMoney()
    override fun restMoney(money: Int): Completable {
        return userMoneySharedPreferences
            .getUserMoney()
            .flatMapCompletable {
                userMoney -> userMoneySharedPreferences.setUserMoney(userMoney - money)
            }
    }
}