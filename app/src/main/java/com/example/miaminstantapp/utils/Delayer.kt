package com.example.miaminstantapp.utils

import javax.inject.Inject

/**
 * Utility class that wraps the coroutines delay calls in order to be able to mock it for tests.
 */
class Delayer @Inject constructor() {
    suspend fun delay(timeInMillis: Long) {
        kotlinx.coroutines.delay(timeInMillis)
    }
}