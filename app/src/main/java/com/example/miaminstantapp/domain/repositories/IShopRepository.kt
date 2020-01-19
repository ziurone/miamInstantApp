package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.ShopEntity
import com.example.miaminstantapp.domain.relations.ShopPurchase
import io.reactivex.Single

interface IShopRepository {
    fun fetchShops(shopsIds: List<Int>): Single<List<ShopEntity>>
    fun fetchShopsPurchase(): Single<List<ShopPurchase>>
}