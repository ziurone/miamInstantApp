package com.example.miaminstantapp.domain.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.miaminstantapp.domain.entities.UserRecipeIngredientEntity
import com.example.miaminstantapp.domain.entities.VolumeUnitEntity

data class UserRecipeIngredientWithVolumeUnit(

    @Embedded
    val userRecipeIngredient: UserRecipeIngredientEntity,

    @Relation(parentColumn = "volumeUnitId", entityColumn = "volumeUnitId")
    val volumeUnit: VolumeUnitEntity
)