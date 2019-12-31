package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.persistence.UserMoneySharedPreferences
import io.reactivex.Completable
import javax.inject.Inject

class UserMoneyRepository @Inject constructor(
    private val userMoneySharedPreferences: UserMoneySharedPreferences
): IUserMoneyRepository {
    override fun setUserMoney(money: Int): Completable = userMoneySharedPreferences.setUserMoney(money)
}