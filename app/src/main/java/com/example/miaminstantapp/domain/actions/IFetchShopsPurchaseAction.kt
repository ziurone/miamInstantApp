package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.ShoppingListArticleEntity
import com.example.miaminstantapp.domain.relations.ShopPurchaseRelation

interface IFetchShopsPurchaseAction: Action<IFetchShopsPurchaseAction.Result> {
    sealed class Result {
        data class Success(val shopPurchaes: List<ShoppingListArticleEntity>): Result()
        data class Error(val message: String): Result()
    }

    fun fetch()
}