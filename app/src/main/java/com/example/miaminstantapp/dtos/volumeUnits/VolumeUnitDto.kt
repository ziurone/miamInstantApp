package com.example.miaminstantapp.dtos.volumeUnits

import com.example.miaminstantapp.domain.entities.VolumeUnitEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VolumeUnitDto(
    val id: Int,
    val name: String
) {
    fun toEntity() = VolumeUnitEntity(
        volumeUnitId = id,
        name = name
    )
}