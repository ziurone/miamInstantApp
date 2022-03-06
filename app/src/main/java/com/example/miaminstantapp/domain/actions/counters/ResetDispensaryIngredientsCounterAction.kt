package com.example.miaminstantapp.domain.actions.counters

import com.example.miaminstantapp.domain.actions.BaseAction
import com.example.miaminstantapp.persistence.DispensaryCounterSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ResetDispensaryIngredientsCounterAction @Inject constructor(
    private val dispensaryCounterSharedPreference: DispensaryCounterSharedPreference
): BaseAction<ResetDispensaryIngredientsCounterAction.Result>() {
    sealed class Result {
        object CounterResetSuccess: ResetDispensaryIngredientsCounterAction.Result()
    }

    fun resetCounter() {
        dispensaryCounterSharedPreference
            .ingredientAdded(0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .track()
    }

    private fun onSuccess() {
        liveData.value = Result.CounterResetSuccess
    }

    override fun getErrorResult(throwable: Throwable): Result? {
        TODO("Not yet implemented")
    }

    override fun getFailureResult(failedResponseCode: String): Result? {
        TODO("Not yet implemented")
    }
}