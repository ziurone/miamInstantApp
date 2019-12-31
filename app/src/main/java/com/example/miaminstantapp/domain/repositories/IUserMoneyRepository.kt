package com.example.miaminstantapp.domain.repositories

import io.reactivex.Completable

interface IUserMoneyRepository {
    fun setUserMoney(money: Int): Completable
}