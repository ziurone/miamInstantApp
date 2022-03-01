package com.example.miaminstantapp.view.recipedetail.presenters

import kotlin.math.floor

class DecimalToFractionsPresenter {

    companion object {
        fun convertDecimalToFraction(x: Double): String {
            if(x == 0.5) {
                return "1/2"
            } else if(x == 0.25){
                return "1/4"
            } else if(x == 0.33) {
                return "1/3"
            } else if(x == 0.66) {
                return "2/3"
            } else {
                return "1"
            }
        }
    }
}