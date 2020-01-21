package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.ShopEntity
import com.example.miaminstantapp.domain.relations.ShopPurchaseRelation
import com.example.miaminstantapp.persistence.ShopDao
import io.reactivex.Single
import javax.inject.Inject

class ShopRepository @Inject constructor(
    val shopDao: ShopDao
): IShopRepository {
    override fun fetchShops(shopsIds: List<Int>): Single<List<ShopEntity>> {
        return Single.just(
            listOf(
                ShopEntity(1, "Coto"),
                ShopEntity(2, "Carrefour")
            )
        )
    }

    override fun fetchShopsPurchase(): Single<List<ShopPurchaseRelation>> = shopDao.getShopPurchase()

}