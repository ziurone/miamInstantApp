package com.example.miaminstantapp.view.utils

class ActionConditionChecker(var action: (Boolean) -> Unit) : ConditionChecker<ActionConditionChecker.Condition>() {
    override fun getConditionInstance(): Condition {
        return Condition()
    }

    override fun updateStatus(): (Boolean) -> Unit {
        return {
            action.invoke(it)
        }
    }

    class Condition : ConditionChecker.Condition<Condition>()

}