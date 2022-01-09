package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import com.example.miaminstantapp.domain.entities.ShoppingListArticleEntity
import com.example.miaminstantapp.persistence.ShopArticleDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ShopArticleRepository @Inject constructor(
    private val shopArticleDao: ShopArticleDao
): IShopArticleRepository {
    override fun insertAll(articles: List<ShoppingListArticleEntity>): Completable = shopArticleDao.insertAll(articles)
    override fun fetch(): Single<List<ShopArticleEntity>> = shopArticleDao.fetch()

    /**
     * Fetch all articles in shopping cart in sended shop.
     */
    override fun fetchByShopId(shopId: Int): Single<List<ShopArticleEntity>> = shopArticleDao.fetchByShopId(shopId)
    override fun cleanPurchase(): Completable = shopArticleDao.deleteAll()
    override fun getPurchaseMoney(): Single<Int> = shopArticleDao.getPurchaseTotal()
    override fun getQuantityInShoppingCart(): Single<Int> = shopArticleDao.getInShoppingCart()
}