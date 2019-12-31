package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import com.example.miaminstantapp.domain.entities.BranchEntity
import io.reactivex.Completable

@Dao
interface BranchDao {

    @Insert
    fun insertAll(branches: List<BranchEntity>): Completable

}