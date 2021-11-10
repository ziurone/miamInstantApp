package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import com.example.miaminstantapp.dtos.volumeUnits.VolumeUnitDto
import com.example.miaminstantapp.dtos.volumeUnits.VolumeUnitsListResponse
import io.reactivex.Completable
import io.reactivex.Single

interface IVolumeUnitRepository {
    fun fetchAll(): Single<VolumeUnitsListResponse>

    fun insertAll(volumeUnits: List<VolumeUnitEntity>): Completable

    fun isEmpty(): Single<Boolean>
}