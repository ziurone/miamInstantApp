package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.UserAddressEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserAddressEntity(
    val street: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        const val TABLE_NAME = "user_addresses"
    }
}