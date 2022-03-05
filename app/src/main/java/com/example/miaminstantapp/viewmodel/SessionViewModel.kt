package com.example.miaminstantapp.viewmodel

import javax.inject.Inject

class SessionViewModel @Inject constructor(): BaseViewModel<SessionViewModel.State>() {
    sealed class State {
        object FetchSuccess: State()
    }
}