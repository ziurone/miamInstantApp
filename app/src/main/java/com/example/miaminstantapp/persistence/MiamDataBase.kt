package com.example.miaminstantapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.miaminstantapp.domain.entities.UserIngredientEntity
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity

@Database(version = 1, exportSchema = false, entities = [UserIngredientEntity::class, VolumeUnitEntity::class])
abstract class MiamDataBase: RoomDatabase() {

    companion object {
        const val NAME = "MIAM_DB"
    }

    abstract fun userIngredientDao(): UserIngredientDao

    abstract fun volumeUnitDao(): VolumeUnitDao
}