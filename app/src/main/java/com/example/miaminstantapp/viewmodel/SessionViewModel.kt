package com.example.miaminstantapp.viewmodel

import com.example.miaminstantapp.persistence.DispensaryCounterSharedPreference
import javax.inject.Inject

class SessionViewModel @Inject constructor(
    private val dispensaryCounterSharedPreference: DispensaryCounterSharedPreference
): BaseViewModel<SessionViewModel.State>() {

    val ingredientsCountLiveData = dispensaryCounterSharedPreference.preferenceLiveData

    sealed class State {
        data class DispensaryCounterChange(val ingredientsCount: Int): State()
    }

    fun listenIngredientsCount() {
        dispensaryCounterSharedPreference.initializeListener()
    }
}