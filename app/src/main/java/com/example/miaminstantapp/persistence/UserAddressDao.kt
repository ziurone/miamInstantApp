package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.miaminstantapp.domain.entities.UserAddressEntity
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface UserAddressDao {
    @Insert
    fun insert(address: UserAddressEntity): Completable

    @Query("SELECT * FROM " + UserAddressEntity.TABLE_NAME + " LIMIT 1")
    fun fetch(): Maybe<UserAddressEntity>

    @Query("DELETE FROM " + UserAddressEntity.TABLE_NAME)
    fun removeAll(): Completable
}