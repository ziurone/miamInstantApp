package com.example.miaminstantapp

import com.example.miaminstantapp.domain.dtos.Ingredient

object TestConstants {
    val INGREDIENT_LIST = listOf<Ingredient>(
        Ingredient(1, "sal", 1, 100, listOf(1,2,3)),
        Ingredient(2, "aceite", 2, 100, listOf(1,2,3)),
        Ingredient(3, "zanahoria", 2, 100, listOf(1,2,3))
    )

    const val GENERIC_ERROR = "generic_error"
}