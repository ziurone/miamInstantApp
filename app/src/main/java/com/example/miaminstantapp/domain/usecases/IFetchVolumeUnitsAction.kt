package com.example.miaminstantapp.domain.usecases

import com.example.miaminstantapp.domain.entities.VolumeUnitEntity

interface IFetchVolumeUnitsAction: Action<IFetchVolumeUnitsAction.Result> {

    sealed class Result {
        data class Success(val volumeUnitsList: List<VolumeUnitEntity>): Result()
        data class Error(val message: String): Result()
    }

    fun fetch()
}