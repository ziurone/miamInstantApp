package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.api.VolumeUnitApi
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import com.example.miaminstantapp.dtos.volumeUnits.VolumeUnitDto
import com.example.miaminstantapp.dtos.volumeUnits.VolumeUnitsListResponse
import com.example.miaminstantapp.persistence.VolumeUnitDao
import io.reactivex.Completable
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class VolumeUnitRepository @Inject constructor(
    private val volumeUnitDao: VolumeUnitDao,
    private val volumeUnitApi: VolumeUnitApi
): IVolumeUnitRepository {

    override fun fetchAll(): Single<VolumeUnitsListResponse> {
        return volumeUnitApi.fetchAll()

    }

    override fun insertAll(volumeUnits: List<VolumeUnitEntity>): Completable {
        return volumeUnitDao.insertAll(volumeUnits)
    }

    override fun isEmpty(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}