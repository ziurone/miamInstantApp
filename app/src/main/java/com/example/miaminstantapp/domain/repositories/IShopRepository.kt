package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.ShopEntity
import com.example.miaminstantapp.domain.entities.ShoppingListArticleEntity
import com.example.miaminstantapp.domain.relations.ShopPurchaseRelation
import io.reactivex.Single

interface IShopRepository {
    fun fetchShops(shopsIds: List<Int>): Single<List<ShopEntity>>
    fun fetchShopsPurchase(): Single<List<ShopPurchaseRelation>>
    fun fetchShopArticlesList(): Single<List<ShoppingListArticleEntity>>
}