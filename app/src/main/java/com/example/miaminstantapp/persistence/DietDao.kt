package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.miaminstantapp.domain.entities.DietEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface DietDao {

    @Insert
    fun insert(diet: DietEntity): Completable

    @Query("SELECT * FROM " + DietEntity.TABLE_NAME)
    fun fetchAll(): Single<List<DietEntity>>
}