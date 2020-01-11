package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import io.reactivex.Completable

interface IShopArticleRepository {
    fun insertAll(articles: List<ShopArticleEntity>): Completable
}