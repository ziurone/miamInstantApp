package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.ShopEntity
import io.reactivex.Single

interface IShopRepository {
    fun fetchShops(shopsIds: List<Int>): Single<List<ShopEntity>>
}