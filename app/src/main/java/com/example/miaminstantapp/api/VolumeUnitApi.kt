package com.example.miaminstantapp.api

import com.example.miaminstantapp.dtos.volumeUnits.VolumeUnitDto
import com.example.miaminstantapp.dtos.volumeUnits.VolumeUnitsListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface VolumeUnitApi {

    @GET("volumeUnit/list")
    fun fetchAll(): Single<VolumeUnitsListResponse>

}