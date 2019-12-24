package com.example.miaminstantapp.domain.actions

import androidx.lifecycle.LiveData

interface Action<T> {
    fun getLiveData(): LiveData<T>

    fun cleanUp()

    fun onError(throwable: Throwable)
}