package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import com.example.miaminstantapp.persistence.ShopArticleDao
import io.reactivex.Completable
import javax.inject.Inject

class ShopArticleRepository @Inject constructor(
    private val shopArticleDao: ShopArticleDao
): IShopArticleRepository {

    override fun insertAll(articles: List<ShopArticleEntity>): Completable = shopArticleDao.insertAll(articles)
}