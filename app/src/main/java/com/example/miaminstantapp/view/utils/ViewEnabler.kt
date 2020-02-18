package com.example.miaminstantapp.view.utils

import android.view.View


private val views: MutableList<View> = mutableListOf()

class ViewEnabler(vararg targetViews: View) : ConditionChecker<ViewEnabler.Condition>() {

    private val views: MutableList<View> = mutableListOf()

    init {
        for (view in targetViews)
            views.add(view)
    }

    override fun getConditionInstance(): Condition {
        return Condition()
    }

    override fun updateStatus(): (Boolean) -> Unit {
        return {
            val status = it
            views.forEach {
                it.isEnabled = status
            }
        }
    }

    class Condition : ConditionChecker.Condition<Condition>()

}