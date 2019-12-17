package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import io.reactivex.Completable
import io.reactivex.Single

interface IVolumeUnitRepository {
    fun fetchAll(): Single<List<VolumeUnitEntity>>

    fun insertAll(): Completable

    fun isEmpty(): Single<Boolean>
}