package com.example.miaminstantapp.domain.actions

interface IFetchShopArticlesQuantityAction: Action<IFetchShopArticlesQuantityAction.Result> {
    sealed class Result {
        data class Success(val quantity: Int): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetchQuantity()
}