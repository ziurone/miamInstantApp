package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class VolumeUnitEntity(

    @PrimaryKey
    val id: Int,
    val name: String
) {
    companion object {
        const val TABLE_NAME = "volumeUnit"
    }

}