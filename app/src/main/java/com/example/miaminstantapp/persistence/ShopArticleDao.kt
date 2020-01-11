package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import io.reactivex.Completable


@Dao
interface ShopArticleDao {
    @Insert
    fun insertAll(articles: List<ShopArticleEntity>): Completable
}