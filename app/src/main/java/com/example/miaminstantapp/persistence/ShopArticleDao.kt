package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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
}