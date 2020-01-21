package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.miaminstantapp.domain.entities.BranchEntity
import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import com.example.miaminstantapp.domain.entities.ShopEntity
import com.example.miaminstantapp.domain.relations.ShopWithBranchesAndArticles
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface ShopArticleDao {
    @Insert
    fun insertAll(articles: List<ShopArticleEntity>): Completable

    @Query("SELECT * FROM " + ShopArticleEntity.TABLE_NAME)
    fun fetch(): Single<List<ShopArticleEntity>>

    @Query("SELECT * FROM " + ShopEntity.TABLE_NAME)
    fun getArticlesWithShop(): Single<List<ShopWithBranchesAndArticles>>

    @Query("SELECT a.* FROM " + ShopArticleEntity.TABLE_NAME  + " a " +
            "INNER JOIN " + BranchEntity.TABLE_NAME + " b ON b.branchId = a.branchId " +
            "INNER JOIN " + ShopEntity.TABLE_NAME + " s ON s.shopId = b.shopId " +
            "WHERE s.shopId = :shopId")
    fun fetchByShopId(shopId: Int): Single<List<ShopArticleEntity>>
}