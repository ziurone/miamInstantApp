package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.BranchEntity.Companion.TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_NAME)
data class BranchEntity (

    @PrimaryKey
    @SerializedName("id")
    val branchId: Int,

    val name: String,

    val lat: String,

    val lng: String,

    val address: String,

    @SerializedName("shop_id")
    val shopId: Int
) {
    companion object {
        const val TABLE_NAME = "branches"
    }
}