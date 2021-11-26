package com.example.miaminstantapp.api

import com.example.miaminstantapp.domain.dtos.IngredientsListResponse
import com.example.miaminstantapp.dtos.volumeUnits.VolumeUnitDto
import com.example.miaminstantapp.dtos.volumeUnits.VolumeUnitsListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface MiamApi {

    @GET("volumeUnit/list")
    fun fetchVolumeUnits(): Single<VolumeUnitsListResponse>

    @GET("ingredients/suggested")
    fun fetchSuggestedIngredients(
        @Query("limit") limit: Int = 5,
        @Query("excludedIngredientsIds[]") excludedIngredients: List<Int>
    ) : Single<IngredientsListResponse>

    @GET("ingredient/search/{query}")
    fun searchIngredient(
        @Path("query") queryText: String
    ): Single<IngredientsListResponse>

}