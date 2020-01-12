package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.relations.ShopWithBranchesAndArticles

interface IFetchShopArticlesAction: Action<IFetchShopArticlesAction.Result> {
    sealed class Result {
        data class Success(val shopsWithArticles: List<ShopWithBranchesAndArticles>): Result()
        data class Error(val errorMessage: String): Result()
    }

    fun fetch()
}