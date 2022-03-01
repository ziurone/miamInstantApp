package com.example.miaminstantapp.domain.actions.recipes_catalog

import com.example.miaminstantapp.domain.actions.BaseAction
import javax.inject.Inject

class FetchRecipeTimeFilterAmountAction @Inject constructor(): BaseAction<FetchRecipeTimeFilterAmountAction.Result>() {
    sealed class Result {
        data class FetchSuccess(val amounts: List<Int>): Result()
    }

    fun fetchAmounts() {
        liveData.value = Result.FetchSuccess(listOf(
            10, 20, 30, 60, 120
        ))
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}