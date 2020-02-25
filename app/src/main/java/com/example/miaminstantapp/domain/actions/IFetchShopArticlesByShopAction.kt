package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.ShopArticleEntity

interface IFetchShopArticlesByShopAction: Action<IFetchShopArticlesByShopAction.Result> {
    sealed class Result {
        data class Success(val articles: List<ShopArticleEntity>): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetchByShop(shopId: Int)
}