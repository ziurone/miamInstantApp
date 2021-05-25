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
            Diet.NONE -> context.getString(R.string.none)
        }
    }

    fun getDescription(diet: Diet) : String {
        return when(diet) {
            Diet.VEGAN -> context.getString(R.string.vegan_description)
            Diet.VEGETARIAN -> context.getString(R.string.vegetarian_description)
            Diet.CELIAC -> context.getString(R.string.celiac_description)
            Diet.NONE -> context.getString(R.string.none_description)
        }
    }

    fun getIcon(diet: Diet, isActive: Boolean) : Int {
        return when(diet) {
            Diet.VEGAN -> if(isActive) R.drawable.ic_lettuce_active else R.drawable.ic_lettuce
            Diet.VEGETARIAN -> if(isActive) R.drawable.ic_salad_bowl_active else R.drawable.ic_salad_bowl
            Diet.CELIAC -> if(isActive) R.drawable.ic_tac_active else R.drawable.ic_tac
            Diet.NONE -> if(isActive) R.drawable.ic_hot_dish_active else R.drawable.ic_hot_dish
        }
    }

    fun getBackground(active: Boolean): Int {
        return if(active) ContextCompat.getColor(context, R.color.primaryLight400)
        else ContextCompat.getColor(context, R.color.secondary_light_100)
    }

}