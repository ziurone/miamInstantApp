package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.entities.DietEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class DietEntity (
    val name: String
) {
    companion object {
        const val TABLE_NAME = "diets"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}