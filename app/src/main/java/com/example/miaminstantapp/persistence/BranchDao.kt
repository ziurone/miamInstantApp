package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.miaminstantapp.domain.entities.BranchEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface BranchDao {

    @Insert
    fun insertAll(branches: List<BranchEntity>): Completable

    @Query("SELECT * FROM " + BranchEntity.TABLE_NAME)
    fun fetchAll(): Single<List<BranchEntity>>

}