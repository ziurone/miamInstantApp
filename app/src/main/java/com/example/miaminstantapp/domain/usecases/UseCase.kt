package com.example.miaminstantapp.domain.usecases

import androidx.lifecycle.LiveData

interface UseCase<T> {
    fun getLiveData(): LiveData<T>

    fun cleanUp()

    fun onError(throwable: Throwable)
}