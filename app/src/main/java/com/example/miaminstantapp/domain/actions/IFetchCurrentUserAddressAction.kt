package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.UserAddressEntity

interface IFetchCurrentUserAddressAction: Action<IFetchCurrentUserAddressAction.Result> {
    sealed class Result {
        data class Success(val userAddress: UserAddressEntity?): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetch()
}