package com.example.miaminstantapp.domain.enums

import com.example.miaminstantapp.domain.entities.DietEntity

enum class Diet {
    VEGAN,
    VEGETARIAN,
    CELIAC;

    fun toEntity(): DietEntity = DietEntity(this.toString())
}