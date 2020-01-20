package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.ShopArticleEntity

interface IFetchShopArticlesAction: Action<IFetchShopArticlesAction.Result> {
    sealed class Result {
        data class Success(val articles: List<ShopArticleEntity>): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetch()
}