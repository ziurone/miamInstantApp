package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.ShopEntity
import io.reactivex.Single
import javax.inject.Inject

class ShopRepository @Inject constructor(): IShopRepository {
    override fun fetchShops(shopsIds: List<Int>): Single<List<ShopEntity>> {
        return Single.just(
            listOf(
                ShopEntity(1, "Coto"),
                ShopEntity(2, "Carrefour")
            )
        )
    }
}