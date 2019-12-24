package com.example.miaminstantapp.domain.actions

import com.example.miaminstantapp.domain.entities.VolumeUnitEntity

interface IAddVolumeUnitsAction: Action<IAddVolumeUnitsAction.Result> {
    sealed class Result {
        object Success: Result()
        data class Error(val message: String): Result()
    }

    fun add(volumeUnits: List<VolumeUnitEntity>)
}