package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import com.example.miaminstantapp.domain.relations.ShopWithBranchesAndArticles
import io.reactivex.Completable
import io.reactivex.Single

interface IShopArticleRepository {
    fun insertAll(articles: List<ShopArticleEntity>): Completable
    fun fetch(): Single<List<ShopWithBranchesAndArticles>>
}