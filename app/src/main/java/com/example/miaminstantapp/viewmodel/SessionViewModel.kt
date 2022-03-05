package com.example.miaminstantapp.viewmodel

import javax.inject.Inject

class SessionViewModel @Inject constructor(): BaseViewModel<SessionViewModel.State>() {
    sealed class State {
        data class DispensaryCounterChange(val ingredientsCount: Int): State()
    }

    fun listenIngredientsCount() {
        setState(State.DispensaryCounterChange(99))
    }
}