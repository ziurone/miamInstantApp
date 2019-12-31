package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import com.example.miaminstantapp.domain.entities.ShopEntity
import io.reactivex.Completable

@Dao
interface ShopDao {

    @Insert
    fun insertAll(shops: List<ShopEntity>): Completable
}