package com.example.miaminstantapp.domain.actions.recipes_catalog

import com.example.miaminstantapp.domain.actions.BaseAction
import com.example.miaminstantapp.domain.repositories.CatalogRecipeTotalTimeMinutesRepository
import com.example.miaminstantapp.domain.repositories.ICatalogRecipeTotalTimeMinutesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchRecipeTimeFilterAmountAction @Inject constructor(
    private val catalogRecipeTotalTimeMinutesRepository: ICatalogRecipeTotalTimeMinutesRepository
): BaseAction<FetchRecipeTimeFilterAmountAction.Result>() {
    sealed class Result {
        data class FetchSuccess(val amounts: List<Int>, val selectedTotalTime: Int): Result()
    }

    fun fetchAmounts() {
        catalogRecipeTotalTimeMinutesRepository
            .getTotalMinutes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess(totalMinutes: Int) {
        liveData.value = Result.FetchSuccess(listOf(
            10, 20, 30, 60, 120
        ), totalMinutes)
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}