package com.example.miaminstantapp.domain.actions

interface ISetUserMoneyAction:  Action<ISetUserMoneyAction.Result>{
    sealed class Result {
        object Success: Result()
        object Error: Result()
    }

    fun set(money: Int)
}