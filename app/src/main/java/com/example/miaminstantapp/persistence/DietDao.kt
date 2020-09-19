package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import com.example.miaminstantapp.domain.entities.DietEntity
import io.reactivex.Completable

@Dao
interface DietDao {

    @Insert
    fun insert(diet: DietEntity): Completable
}