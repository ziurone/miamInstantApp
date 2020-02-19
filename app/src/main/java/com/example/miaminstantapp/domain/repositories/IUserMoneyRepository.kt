package com.example.miaminstantapp.domain.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface IUserMoneyRepository {
    fun setUserMoney(money: Int): Completable
    fun getUserMoney(): Observable<Int>
}