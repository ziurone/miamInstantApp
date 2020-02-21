package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.UserAddressEntity

interface IAddUserAddressAction: Action<IAddUserAddressAction.Result> {
    sealed class Result {
        object Success: Result()
        data class Error(val errorMessage: String): Result()
    }

    fun add(address: UserAddressEntity)
}