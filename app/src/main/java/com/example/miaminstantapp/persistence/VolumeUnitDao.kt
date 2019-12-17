package com.example.miaminstantapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import io.reactivex.Completable

@Dao
interface VolumeUnitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(volumeUnits: List<VolumeUnitEntity>): Completable

}