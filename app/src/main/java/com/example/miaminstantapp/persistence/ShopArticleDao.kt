package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.miaminstantapp.domain.entities.BranchEntity
import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import com.example.miaminstantapp.domain.entities.ShopEntity
import com.example.miaminstantapp.domain.entities.ShoppingListArticleEntity
import com.example.miaminstantapp.domain.relations.ShopWithBranchesAndArticles
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface ShopArticleDao {

    @Insert
    fun insertAll(articles: List<ShoppingListArticleEntity>): Completable

    @Insert
    fun insertAllLegacy(articles: List<ShopArticleEntity>): Completable

    @Query("SELECT * FROM " + ShopArticleEntity.TABLE_NAME)
    fun fetch(): Single<List<ShopArticleEntity>>

    @Query("SELECT * FROM " + ShopEntity.TABLE_NAME)
    fun getArticlesWithShop(): Single<List<ShopWithBranchesAndArticles>>

    @Query("SELECT * FROM " + ShoppingListArticleEntity.TABLE_NAME)
    fun fetchArticleList(): Single<List<ShoppingListArticleEntity>>

    @Query("SELECT a.* FROM " + ShopArticleEntity.TABLE_NAME  + " a " +
            "INNER JOIN " + BranchEntity.TABLE_NAME + " b ON b.branchId = a.branchId " +
            "INNER JOIN " + ShopEntity.TABLE_NAME + " s ON s.shopId = b.shopId " +
            "WHERE s.shopId = :shopId")
    fun fetchByShopId(shopId: Int): Single<List<ShopArticleEntity>>

    @Query("DELETE FROM " + ShopArticleEntity.TABLE_NAME)
    fun deleteAll(): Completable

    @Query("SELECT SUM(totalPrice) as purchasePrice FROM " + ShopArticleEntity.TABLE_NAME)
    fun getPurchaseTotal(): Single<Int>

    @Query("SELECT COUNT(id) FROM " + ShopArticleEntity.TABLE_NAME)
    fun getInShoppingCart(): Single<Int>
}