package com.example.miaminstantapp.view.recipes_catalog

import com.example.miaminstantapp.domain.enums.Diet

class DietsPresenter {
    companion object {
        fun getLocalizedName(diet: Diet): String {
            return when(diet) {
                Diet.VEGAN -> "Vegana"
                Diet.VEGETARIAN -> "Vegetariana"
                Diet.CELIAC -> "Celiaca"
                Diet.NONE -> "Omnivora"
            }
        }

        fun getDietFromLocalizedName(name: String): Diet {
            return when(name) {
                "Vegana" -> Diet.VEGAN
                "Vegetariana" -> Diet.VEGETARIAN
                "Celiaca" -> Diet.CELIAC
                "Omnivora" -> Diet.NONE
                else -> Diet.NONE
            }
        }
    }
}