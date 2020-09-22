package com.example.miaminstantapp.view.formatters

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.miaminstantapp.R
import com.example.miaminstantapp.domain.enums.Diet

class DietItemsPresenter constructor(
    private val context: Context
) {

    fun getTitle(diet: Diet) : String {
        return when(diet) {
            Diet.VEGAN -> context.getString(R.string.vegan_name)
            Diet.VEGETARIAN -> context.getString(R.string.vegetarian_name)
            Diet.CELIAC -> context.getString(R.string.celiac_name)
        }
    }

    fun getDescription(diet: Diet) : String {
        return when(diet) {
            Diet.VEGAN -> context.getString(R.string.vegan_description)
            Diet.VEGETARIAN -> context.getString(R.string.vegetarian_description)
            Diet.CELIAC -> context.getString(R.string.celiac_description)
        }
    }

    fun getIcon(diet: Diet) : Int {
        return when(diet) {
            Diet.VEGAN -> R.drawable.ic_lettuce
            Diet.VEGETARIAN -> R.drawable.ic_salad_bowl
            Diet.CELIAC -> R.drawable.ic_tac
        }
    }

    fun getBackground(active: Boolean): Int {
        return if(active) ContextCompat.getColor(context, R.color.secondary)
        else ContextCompat.getColor(context, R.color.secondary_light)
    }

}