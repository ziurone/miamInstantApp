package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import io.reactivex.Completable
import io.reactivex.Single

interface IShopArticleRepository {
    fun insertAll(articles: List<ShopArticleEntity>): Completable
    fun fetch(): Single<List<ShopArticleEntity>>
    fun fetchByShopId(shopId: Int): Single<List<ShopArticleEntity>>
    fun cleanPurchase(): Completable
    fun getPurchaseMoney(): Single<Int>
    fun getQuantityInShoppingCart(): Single<Int>
}