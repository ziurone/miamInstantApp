package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.ShopEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ShopEntity(
    @PrimaryKey
    val shopId: Int,
    val name: String
) {
    companion object {
        const val TABLE_NAME = "shops"
    }
}