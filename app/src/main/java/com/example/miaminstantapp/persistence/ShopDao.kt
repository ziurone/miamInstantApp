package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.miaminstantapp.domain.entities.BranchEntity
import com.example.miaminstantapp.domain.entities.ShopArticleEntity
import com.example.miaminstantapp.domain.entities.ShopEntity
import com.example.miaminstantapp.domain.relations.ShopPurchase
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ShopDao {

    @Insert
    fun insertAll(shops: List<ShopEntity>): Completable

    @Query("SELECT s.*, SUM(a.totalPrice) as purchasePrice FROM "
            + ShopEntity.TABLE_NAME + " s " +
            " LEFT JOIN " + BranchEntity.TABLE_NAME + " b ON b.shopId = s.shopId" +
            " LEFT JOIN " + ShopArticleEntity.TABLE_NAME + " a ON a.branchId = b.branchId ")
    fun getShopPurchase(): Single<List<ShopPurchase>>
}