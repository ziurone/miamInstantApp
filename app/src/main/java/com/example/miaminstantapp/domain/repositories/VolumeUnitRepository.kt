package com.example.miaminstantapp.domain.repositories

import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import com.example.miaminstantapp.persistence.VolumeUnitDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class VolumeUnitRepository @Inject constructor(
    private val volumeUnitDao: VolumeUnitDao
): IVolumeUnitRepository {

    override fun fetchAll(): Single<List<VolumeUnitEntity>> {
        return Single.just(
            listOf(
                VolumeUnitEntity(1, "Cuchara"),
                VolumeUnitEntity(2, "Taza"),
                VolumeUnitEntity(3, "Kilo")
            )
        )
    }

    override fun insertAll(volumeUnits: List<VolumeUnitEntity>): Completable {
        return volumeUnitDao.insertAll(volumeUnits)
    }

    override fun isEmpty(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}