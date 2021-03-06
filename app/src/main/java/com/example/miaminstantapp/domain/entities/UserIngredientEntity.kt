package com.example.miaminstantapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miaminstantapp.domain.dtos.Ingredient
import com.example.miaminstantapp.domain.entities.UserIngredientEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserIngredientEntity(
    @PrimaryKey
    val ingredientId: Int,
    val name: String,
    val volumeUnitId: Int,
    val volumeUnitQuantity: Int
) {
    companion object {
        const val TABLE_NAME = "userIngredient"
    }
}

fun Ingredient.toUserIngredientEntity(): UserIngredientEntity {
    return UserIngredientEntity(
        ingredientId = id,
        name = name,
        volumeUnitId = defaultVolumeUnitId,
        volumeUnitQuantity = defaultVolumeUnitQuantity
    )
}