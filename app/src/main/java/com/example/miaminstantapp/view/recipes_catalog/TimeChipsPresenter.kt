package com.example.miaminstantapp.view.recipes_catalog

class TimeChipsPresenter {
    companion object {
        fun getChipText(minutes: Int): String {
            return if(minutes < 60) minutes.toString() + "min"
            else {
                val hours = minutes / 60
                if(hours == 1) hours.toString() + "h"
                else hours.toString() + "hs"
            }
        }

        fun getMinutesByChipText(chipText: String): Int {
            val isHour = chipText.contains("h") || chipText.contains("hs")
            val number = chipText.filter{ it.isDigit() }

            return if(isHour) ((number.toInt()) * 60) else number.toInt()
        }
    }
}