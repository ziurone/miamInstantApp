package com.example.miaminstantapp.dtos.volumeUnits

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VolumeUnitsListResponse(val volumeUnits: List<VolumeUnitDto>)