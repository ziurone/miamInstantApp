package com.example.miaminstantapp.dtos

import com.example.miaminstantapp.domain.entities.BranchEntity

data class BranchDto(
        val id: Int,
        val name: String,
        val shopId: Int,
        val address: String,
        val lat: String,
        val lng: String,
        val shopType: String,
        val branchType: String
) {
    fun toModel() = BranchEntity(
            branchId = id,
            name = name,
            lat = lat,
            lng = lng,
            address = address,
            shopId = shopId
    )
}