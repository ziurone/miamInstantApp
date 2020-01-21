package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.ShopPurchaseRelation

interface IFetchShopsPurchaseAction: Action<IFetchShopsPurchaseAction.Result> {
    sealed class Result {
        data class Success(val shopPurchaes: List<ShopPurchaseRelation>): Result()
        data class Error(val message: String): Result()
    }

    fun fetch()
}